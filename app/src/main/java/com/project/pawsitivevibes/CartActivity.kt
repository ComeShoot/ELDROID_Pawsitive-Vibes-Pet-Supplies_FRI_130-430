package com.project.pawsitivevibes


import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private lateinit var decrementButton1: Button
    private lateinit var incrementButton1: Button
    private lateinit var quantityText1: TextView
    private lateinit var checkbox1: CheckBox
    private lateinit var checkoutButton: Button

    private var quantity1 = 1
    private var pricePerItem = 500.00
    private var subtotal = 214.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        decrementButton1 = findViewById(R.id.decrementButton1)
        incrementButton1 = findViewById(R.id.incrementButton1)
        quantityText1 = findViewById(R.id.quantityText1)
        checkbox1 = findViewById(R.id.checkbox1)
        checkoutButton = findViewById(R.id.checkoutButton)

        decrementButton1.setOnClickListener {
            if (quantity1 > 1) {
                quantity1--
                quantityText1.text = quantity1.toString()
                updateSubtotal()
            }
        }

        incrementButton1.setOnClickListener {
            quantity1++
            quantityText1.text = quantity1.toString()
            updateSubtotal()
        }

        checkoutButton.setOnClickListener {
            Toast.makeText(this, "Checked Out", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateSubtotal() {
        subtotal = quantity1 * pricePerItem
        findViewById<TextView>(R.id.totalAmount).text = "₱${"%.2f".format(subtotal)}"
    }
}

