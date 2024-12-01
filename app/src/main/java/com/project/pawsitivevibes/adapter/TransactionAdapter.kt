package com.project.pawsitivevibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.TransactionItem

class TransactionAdapter(private val transactions: List<TransactionItem>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val merchantName: TextView = view.findViewById(R.id.merchantStore)
        val productName: TextView = view.findViewById(R.id.productTitle)
        val productQuantity: TextView = view.findViewById(R.id.productQuantity)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val totalPrice: TextView = view.findViewById(R.id.totalPrice)
        val orderStatusButton: Button = view.findViewById(R.id.orderStatusButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_transaction_customer_completed, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.productImage.setImageResource(transaction.imageResId)
        holder.merchantName.text = transaction.merchantName
        holder.productName.text = transaction.productName
        holder.productQuantity.text = transaction.productQuantity.toString()
        holder.productDescription.text = transaction.productDescription
        holder.totalPrice.text = transaction.totalPrice.toString()
        holder.orderStatusButton.text = transaction.orderStatus
    }

    override fun getItemCount(): Int = transactions.size
}
