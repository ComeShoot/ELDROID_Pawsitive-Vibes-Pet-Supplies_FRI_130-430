package com.project.pawsitivevibes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.model.Item

class ItemAdapter(
    private val products: MutableList<AllProduct>,
    private val onAddToCartClick: (AllProduct, Int, Double) -> Unit // Updated to pass quantity and price
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.productTitle)
        val description: TextView = view.findViewById(R.id.productDescription)
        val itemImage: ImageView = view.findViewById(R.id.productImage)
        val itemPrice: TextView = view.findViewById(R.id.productPrice)
        val itemQuantity: TextView = view.findViewById(R.id.productQuantity)
        val addToCartButton: ImageButton = view.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_dashboard_customer, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = products[position] // AllProduct object

        Glide.with(holder.itemView.context)
            .load("http://10.0.2.2:8000/" + product.prodImage) // Assuming prodImage is a URL or path
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(holder.itemImage)

        holder.title.text = product.prodName
        holder.description.text = product.prodDescription
        holder.itemPrice.text = "₱${product.prodPrice}"

        // Handle the Add to Cart Button click
        holder.addToCartButton.setOnClickListener {
            val quantity = holder.itemQuantity.text.toString().toIntOrNull() ?: 1 // Default to 1 if invalid
            onAddToCartClick(product, quantity, product.prodPrice)
        }
    }

    fun updateData(newProducts: List<AllProduct>) {
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size
}





