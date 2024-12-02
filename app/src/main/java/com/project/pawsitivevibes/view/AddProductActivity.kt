package com.project.pawsitivevibes.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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

        // Check for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION
            )
        } else {
            setupListeners()  // Set up listeners if permission is already granted
        }

        // Initialize ViewModel with the correct factory
        val repository = ProductRepository(ApiService.create())  // Create the repository
        val factory =
            ViewModelFactory(repository)  // Create the factory with the repository
        viewModel =
            ViewModelProvider(this, factory)[AddProductViewModel::class.java]  // Correct ViewModelProvider usage

        findViewById<ImageButton>(R.id.add_photo).setOnClickListener {
            // Handle image picker logic here
            pickImageFromGallery()
        }

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            uploadProduct()
        }

        // Observe the result of the upload operation
        viewModel.uploadResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Product uploaded successfully!", Toast.LENGTH_SHORT).show()
            }.onFailure { exception ->
                Toast.makeText(this, "Failed to upload product: ${exception.message}", Toast.LENGTH_SHORT).show()
                Log.e("AddProductActivity", "Error uploading product", exception)
            }
        }

    }

    private fun setupListeners() {
        findViewById<ImageButton>(R.id.add_photo).setOnClickListener {
            // Handle image picker logic here
            pickImageFromGallery()
        }

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            uploadProduct()
        }
        // Observe the result of the upload operation
        viewModel.uploadResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Product uploaded successfully!", Toast.LENGTH_SHORT).show()
            }.onFailure { exception ->
                Toast.makeText(this, "Failed to upload product: ${exception.message}", Toast.LENGTH_SHORT).show()
                Log.e("AddProductActivity", "Error uploading product", exception)
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with the image picker
                    setupListeners()
                } else {
                    // Permission denied, show a message
                    Toast.makeText(this, "Permission denied to read storage", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun uploadProduct() {
        val name = findViewById<EditText>(R.id.createPost).text.toString()
        val description = findViewById<EditText>(R.id.description).text.toString()
        val price = findViewById<EditText>(R.id.price).text.toString().toDoubleOrNull() ?: 0.0
        val quantity = findViewById<TextView>(R.id.text_quantity).text.toString().toInt()
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val authToken = sharedPreferences.getString("auth_token", null)



        if (name.isEmpty() || description.isEmpty() || price == 0.0 || quantity == 0 || selectedImageFile == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Upload the product using the ViewModel
        if (authToken != null) {
            viewModel.uploadProduct(name, description, price, quantity, selectedImageFile!!, authToken)
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
                // Use the URI directly or get the real path if needed
                selectedImageFile = File(getRealPathFromURI(it))  // Adjust as needed for scoped storage
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
        return null // Return null if the path could not be retrieved
    }


}
