package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.TransactionReceive

class TransactionReceiveViewModel : ViewModel() {
    private val _transactions = MutableLiveData<List<TransactionReceive>>()
    val transactions: LiveData<List<TransactionReceive>> get() = _transactions

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        val dummyData = listOf(
            TransactionReceive(
                profileImageRes = R.drawable.ic_person, // Replace with a valid drawable resource
                merchantStoreName = "John's Grocery",
                productTitle = "Organic Apples",
                productDescription = "Fresh and crisp organic apples.",
                productPrice = "$3.00",
                itemQuantity = "3 pcs",
                totalPrice = "$9.00",
                deliveryDestination = "123 Elm Street, New York, NY"
            ),
            TransactionReceive(
                profileImageRes = R.drawable.ic_person, // Replace with a valid drawable resource
                merchantStoreName = "City Electronics",
                productTitle = "Wireless Headphones",
                productDescription = "Noise-cancelling wireless headphones.",
                productPrice = "$150.00",
                itemQuantity = "1 unit",
                totalPrice = "$150.00",
                deliveryDestination = "45 Tech Park, San Francisco, CA"
            )
        )
        _transactions.value = dummyData
    }
}
