package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up notification item data
        val itemImage = view.findViewById<ImageView>(R.id.itemImage)
        val itemTitle = view.findViewById<TextView>(R.id.itemTitle)
        val itemMessage = view.findViewById<TextView>(R.id.itemMessage)
        val itemTime = view.findViewById<TextView>(R.id.itemTime)

        // Set data for the notification
        itemImage.setImageResource(R.drawable.ic_product)
        itemTitle.text = "Olive Shampoo"
        itemMessage.text = "Parcel has departed from sorting facility"
        itemTime.text = "6:45 PM"
    }
}