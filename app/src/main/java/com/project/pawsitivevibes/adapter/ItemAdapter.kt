package com.project.pawsitivevibes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.profile) // Replace with your placeholder
            .error(R.drawable.profile) // Replace with your error image
            .into(holder.itemImage)

        holder.title.text = item.name
        holder.description.text = item.description
        holder.itemPrice.text = item.price.toString()
        holder.itemQuantity.text = item.quantity.toString()

    }

    override fun getItemCount(): Int = items.size
}

