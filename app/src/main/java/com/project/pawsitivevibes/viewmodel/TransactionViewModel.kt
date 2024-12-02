package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.TransactionItem

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableLiveData<List<TransactionItem>>()
    val transactions: LiveData<List<TransactionItem>> = _transactions

    init {
        loadDummyTransactions()
    }

    private fun loadDummyTransactions() {
        _transactions.value = listOf(
            TransactionItem(
                imageResId = R.drawable.eye_drops, // Replace with actual product image drawable
                merchantName = "Pet Supplies Co.",
                productName = "Olive Shampoo",
                productQuantity = 1,
                productDescription = "For Dogs and Cats",
                totalPrice = 500,
                orderStatus = "Order Received"
            ),
            TransactionItem(
                imageResId = R.drawable.magic_milk, // Replace with actual product image drawable
                merchantName = "Animal Care",
                productName = "Premium Dog Food",
                productQuantity = 3,
                productDescription = "Nutritious and healthy",
                totalPrice = 1200,
                orderStatus = "Delivered"
            )
        )
    }
}
