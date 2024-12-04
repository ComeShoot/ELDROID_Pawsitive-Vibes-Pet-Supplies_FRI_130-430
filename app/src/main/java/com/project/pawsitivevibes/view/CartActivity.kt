package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.CartAdapter
import com.project.pawsitivevibes.viewmodel.AllProductViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var checkoutButton: Button
    private lateinit var closeButton: ImageButton
    private lateinit var recyclerView: RecyclerView

    private val viewModel: AllProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        checkoutButton = findViewById(R.id.CheckOutBtn)
        closeButton = findViewById(R.id.closebtn)
        recyclerView = findViewById(R.id.allDonationRecycler)


        val cartAdapter = CartAdapter(mutableListOf(), onQuantityChange = { cartItem, newQuantity ->
            // Handle quantity change here (e.g., update backend or local data)
        }, onItemChecked = { cartItem, isChecked ->
            // Handle item selection here
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        // Update data when you fetch cart items
        viewModel.cartItems.observe(this) { cartItems ->
            Log.d("CartActivity", "Fetched cart items: $cartItems")  // Debugging log
            if (cartItems != null) {
                cartAdapter.updateCartItems(cartItems)
            }
        }
        viewModel.fetchCartItems()

        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }

        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
