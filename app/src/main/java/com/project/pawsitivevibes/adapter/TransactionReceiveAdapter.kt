package com.project.pawsitivevibes.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.TransactionReceive

class TransactionReceiveAdapter(
    private val transactions: List<TransactionReceive>
) : RecyclerView.Adapter<TransactionReceiveAdapter.TransactionViewHolder>() {

    // ViewHolder class for caching views
    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePic: ImageView = itemView.findViewById(R.id.profilePic)
        val merchantStore: TextView = itemView.findViewById(R.id.merchantStore)
        val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val itemQuantity: TextView = itemView.findViewById(R.id.itemQuantity)
        val totalPrice: TextView = itemView.findViewById(R.id.totalPrice)
        val destinationDelivery: TextView = itemView.findViewById(R.id.destinationDelivery)
    }

    // Create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_transaction_customer_toreceived, parent, false)
        return TransactionViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]

        // Bind data to the views
        holder.profilePic.setImageResource(transaction.profileImageRes)
        holder.merchantStore.text = transaction.merchantStoreName
        holder.productTitle.text = transaction.productTitle
        holder.productDescription.text = transaction.productDescription
        holder.productPrice.text = transaction.productPrice
        holder.itemQuantity.text = transaction.itemQuantity
        holder.totalPrice.text = transaction.totalPrice
        holder.destinationDelivery.text = transaction.deliveryDestination
    }

    // Return the size of the data list
    override fun getItemCount(): Int = transactions.size
}
