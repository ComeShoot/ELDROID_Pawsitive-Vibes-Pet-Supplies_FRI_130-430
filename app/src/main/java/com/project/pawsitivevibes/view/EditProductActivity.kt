package com.project.pawsitivevibes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.repository.ItemRepository
import com.project.pawsitivevibes.viewmodel.ItemViewModelFactory
import com.project.pawsitivevibes.viewmodel.SellerProductsViewModel

class EditProductActivity : AppCompatActivity() {

    private lateinit var viewModel: SellerProductsViewModel
    private lateinit var productName: EditText
    private lateinit var productDescription: EditText
    private lateinit var productPrice: EditText
    private lateinit var productQuantity: TextView
    private lateinit var productImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val repository = ItemRepository(ApiService.create())
        val factory = ItemViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[SellerProductsViewModel::class.java]

        productName = findViewById(R.id.createPost)
        productDescription = findViewById(R.id.description)
        productPrice = findViewById(R.id.price)
        productQuantity = findViewById(R.id.text_quantity)
        productImage = findViewById(R.id.photoImageView)

        val productId = intent.getIntExtra("product_id", -1)
        val name = intent.getStringExtra("product_name")
        val description = intent.getStringExtra("product_description")
        val price = intent.getDoubleExtra("product_price", 0.0)
        val quantity = intent.getIntExtra("product_quantity", 0)
        val imageUrl = intent.getStringExtra("product_image")

        productName.setText(name)
        productDescription.setText(description)
        productPrice.setText(price.toString())
        productQuantity.text = quantity.toString()

        if (!imageUrl.isNullOrEmpty()) {
            val fullImageUrl = "http://10.0.2.2:8000/$imageUrl"
            Glide.with(this)
                .load(fullImageUrl)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(productImage)
        }

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            val name = productName.text.toString()
            val description = productDescription.text.toString()
            val price = productPrice.text.toString().toDouble()
            val quantity = productQuantity.text.toString().toInt()

            if (productId != -1) {
                viewModel.updateProduct(productId, name, description, price, quantity)
            } else {
                Toast.makeText(this, "Product ID is missing", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.updateResult.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
                finish() // Finish the current activity to prevent returning to it
            } else {
                Toast.makeText(this, "Failed to update product", Toast.LENGTH_SHORT).show()
            }
        }


        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }


    }
}
