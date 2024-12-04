package com.project.pawsitivevibes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.databinding.LayoutCartBinding
import com.project.pawsitivevibes.model.CartItem
import com.project.pawsitivevibes.model.Item

class CartAdapter(
    private val cartItems: MutableList<CartItem>, // Replace CartItem with your model
    private val onQuantityChange: (CartItem, Int) -> Unit, // Callback for quantity change
    private val onItemChecked: (CartItem, Boolean) -> Unit // Callback for item selection
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productCheckbox: CheckBox = view.findViewById(R.id.productCheckbox)
        val storeName: TextView = view.findViewById(R.id.storeName)
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productTitle: TextView = view.findViewById(R.id.productTitle)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val buttonDecrement: Button = view.findViewById(R.id.button_decrement)
        val textQuantity: TextView = view.findViewById(R.id.text_quantity)
        val buttonIncrement: Button = view.findViewById(R.id.button_increment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        // Load product image
        Glide.with(holder.itemView.context)
            .load("http://10.0.2.2:8000/" + cartItem.productImage) // Replace with your image URL property
            .placeholder(R.drawable.profile) // Placeholder image
            .error(R.drawable.profile) // Error image
            .into(holder.productImage)
        Log.d("CartAdapter", "Image URL: http://10.0.2.2:8000/" + cartItem.productImage)

        // Set product details
        holder.storeName.text = cartItem.storeName
        holder.productTitle.text = cartItem.productName
        holder.productDescription.text = cartItem.productDescription
        holder.productPrice.text = "₱${cartItem.productPrice}"
        holder.textQuantity.text = cartItem.quantity.toString()

        // Handle quantity increment and decrement
        holder.buttonDecrement.setOnClickListener {
            val newQuantity = (holder.textQuantity.text.toString().toInt() - 1).coerceAtLeast(1)
            holder.textQuantity.text = newQuantity.toString()
            onQuantityChange(cartItem, newQuantity)
        }

        holder.buttonIncrement.setOnClickListener {
            val newQuantity = holder.textQuantity.text.toString().toInt() + 1
            holder.textQuantity.text = newQuantity.toString()
            onQuantityChange(cartItem, newQuantity)
        }

        // Handle checkbox selection
        holder.productCheckbox.setOnCheckedChangeListener(null) // Prevent unwanted callbacks
        holder.productCheckbox.isChecked = cartItem.isSelected
        holder.productCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onItemChecked(cartItem, isChecked)
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateCartItems(newItems: List<CartItem>) {
        Log.d("CartAdapter", "Updating cart items: $newItems")  // Add this line for debugging
        cartItems.clear()
        cartItems.addAll(newItems)
        notifyDataSetChanged()
    }

}
