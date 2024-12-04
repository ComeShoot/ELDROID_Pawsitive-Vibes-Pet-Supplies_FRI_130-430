package com.project.pawsitivevibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.Item

class ItemSellerAdapter(
    private val items: List<Item>,
    private val onMoreOptionsClick: (Item) -> Unit,
    private val onDeleteClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemSellerAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.productTitle)
        val description: TextView = view.findViewById(R.id.productDescription)
        val itemImage: ImageView = view.findViewById(R.id.productImage)
        val itemPrice: TextView = view.findViewById(R.id.productPrice)
        val itemQuantity: TextView = view.findViewById(R.id.productQuantity)
        val removeButton: ImageButton = view.findViewById(R.id.removeButton)
        val moreOptionsButton: ImageButton = view.findViewById(R.id.moreOptionsButton)
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

        val imageUrl = "http://10.0.2.2:8000/${item.imageUrl}"
        Glide.with(holder.itemImage.context)
            .load(imageUrl)
            .placeholder(R.drawable.profile)
            .into(holder.itemImage)

        // Handle moreOptionsButton click
        holder.removeButton.setOnClickListener {
            onDeleteClick(item) // Handle delete button click
        }
        holder.moreOptionsButton.setOnClickListener { onMoreOptionsClick(item) }
    }

    override fun getItemCount(): Int = items.size
}


