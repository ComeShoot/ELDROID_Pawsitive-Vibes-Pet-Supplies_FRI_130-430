package com.project.pawsitivevibes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Set up notification item data
        val itemImage = findViewById<ImageView>(R.id.itemImage)
        val itemTitle = findViewById<TextView>(R.id.itemTitle)
        val itemMessage = findViewById<TextView>(R.id.itemMessage)
        val itemTime = findViewById<TextView>(R.id.itemTime)

        // Set data for the notification
        itemImage.setImageResource(R.drawable.ic_product) // Use your product image here
        itemTitle.text = "Olive Shampoo"
        itemMessage.text = "Parcel has departed from sorting facility"
        itemTime.text = "6:45 PM"
    }
}

