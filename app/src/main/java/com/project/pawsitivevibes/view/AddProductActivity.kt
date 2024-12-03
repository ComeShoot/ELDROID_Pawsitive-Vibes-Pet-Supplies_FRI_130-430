package com.project.pawsitivevibes.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.repository.ProductRepository
import com.project.pawsitivevibes.viewmodel.AddProductViewModel
import com.project.pawsitivevibes.viewmodel.ViewModelFactory
import java.io.File

class AddProductActivity : AppCompatActivity() {

    private lateinit var viewModel: AddProductViewModel
    private var selectedImageFile: File? = null
    private val REQUEST_CODE_PERMISSION = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // Initialize ViewModel
        val repository = ProductRepository(ApiService.create())
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AddProductViewModel::class.java]

        // Check for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        } else {
            setupListeners()
        }

        // Observe quantity changes
        val quantityTextView = findViewById<TextView>(R.id.text_quantity)
        viewModel.quantity.observe(this) { quantity ->
            quantityTextView.text = quantity.toString()
        }

        // Increment and decrement buttons
        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementQuantity()
        }

        findViewById<Button>(R.id.button_decrement).setOnClickListener {
            viewModel.decrementQuantity()
        }

        // Handle image selection
        findViewById<ImageButton>(R.id.add_photo).setOnClickListener {
            pickImageFromGallery()
        }

        // Handle product upload
        findViewById<Button>(R.id.addBtn).setOnClickListener {
            uploadProduct()
        }

        // Observe upload result
        viewModel.uploadResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Product uploaded successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }.onFailure { exception ->
                Toast.makeText(this, "Failed to upload product: ${exception.message}", Toast.LENGTH_SHORT).show()
                Log.e("AddProductActivity", "Error uploading product", exception)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupListeners()
            } else {
                Toast.makeText(this, "Permission denied to read storage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() {
        // Set up listeners for image picker and upload
        findViewById<ImageButton>(R.id.add_photo).setOnClickListener {
            pickImageFromGallery()
        }

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            uploadProduct()
        }
    }

    private fun uploadProduct() {
        val name = findViewById<EditText>(R.id.createPost).text.toString()
        val description = findViewById<EditText>(R.id.description).text.toString()
        val price = findViewById<EditText>(R.id.price).text.toString().toDoubleOrNull() ?: 0.0
        val quantity = viewModel.quantity.value ?: 1
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val authToken = sharedPreferences.getString("auth_token", null)

        if (name.isEmpty() || description.isEmpty() || price == 0.0 || quantity == 0 || selectedImageFile == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (authToken != null) {
            viewModel.uploadProduct(name, description, price, selectedImageFile!!, authToken)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val imageUri = data?.data
            imageUri?.let {
                selectedImageFile = File(getRealPathFromURI(it))
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val idx = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            if (idx != -1) {
                val path = it.getString(idx)
                it.close()
                return path
            }
            it.close()
        }
        return null
    }
}
