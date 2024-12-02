package com.project.pawsitivevibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.Item

class ItemSellerAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemSellerAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.productTitle)
        val description: TextView = view.findViewById(R.id.productDescription)
        val itemImage: ImageView = view.findViewById(R.id.productImage)
        val itemPrice: TextView = view.findViewById(R.id.productPrice)
        val itemQuantity: TextView = view.findViewById(R.id.productQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_dashboard, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        // Bind data
        holder.title.text = item.name
        holder.description.text = item.description
        holder.itemPrice.text = "₱${item.price}"
        holder.itemQuantity.text = "x${item.quantity}"

        // Construct the full URL for the image
        val imageUrl = "http://10.0.2.2:8000/${item.imageUrl}"

        // Load image with Glide
        Glide.with(holder.itemImage.context)
            .load(imageUrl) // Use the full URL here
            .placeholder(R.drawable.profile) // Fallback image while loading
            .into(holder.itemImage)

        // Handle item click
        holder.itemView.setOnClickListener { onItemClick(item) }
    }


    override fun getItemCount(): Int = items.size
}


