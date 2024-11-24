package com.project.pawsitivevibes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.Item

class ItemAdapter(private val items: List<Item>, private val onAddToCartClick: (Item) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.productTitle)
        val description: TextView = view.findViewById(R.id.productDescription)
        val itemImage: ImageView = view.findViewById(R.id.productImage)
        val itemPrice: TextView = view.findViewById(R.id.productPrice)
        val itemQuantity: TextView = view.findViewById(R.id.productQuantity)
        val addToCartButton: ImageView = view.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_dashboard_customer, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemImage.setImageResource(item.imageResId)
        holder.title.text = item.title
        holder.description.text = item.description
        holder.itemPrice.text = item.price
        holder.itemQuantity.text = item.quantity

        // Set onClickListener for the addToCartButton
        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(item) // Pass the item clicked to the onClick handler
        }
    }

    override fun getItemCount(): Int = items.size
}

