package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.pawsitivevibes.R

class CartActivity : AppCompatActivity() {

    private lateinit var checkoutButton: Button
    private lateinit var closeButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        checkoutButton = findViewById(R.id.CheckOutBtn)
        closeButton = findViewById(R.id.closebtn)

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
