package com.project.pawsitivevibes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.databinding.LayoutCartBinding
import com.project.pawsitivevibes.model.CartItem
import com.project.pawsitivevibes.model.Item

class CartAdapter(private val items: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = LayoutCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class CartViewHolder(private val binding: LayoutCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.storeName.text = item.storeName
            binding.productTitle.text = item.productTitle
            binding.productDescription.text = item.productDescription
            binding.productPrice.text = item.productPrice
            binding.productImage.setImageResource(item.productImage)
            binding.textQuantity.text = item.quantity.toString()
        }
    }
}
