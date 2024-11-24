package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.adapter.CartAdapter
import com.project.pawsitivevibes.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var checkoutButton: Button
    private lateinit var closeButton: ImageButton
    private lateinit var recyclerView: RecyclerView

    // Use ViewModel to manage data
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        checkoutButton = findViewById(R.id.CheckOutBtn)
        closeButton = findViewById(R.id.closebtn)
        recyclerView = findViewById(R.id.allDonationRecycler)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartViewModel.items.observe(this, { items ->
            // Set the adapter with the observed items
            recyclerView.adapter = CartAdapter(items)
        })

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
