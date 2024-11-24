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
                merchantStoreName = "John's PetVet",
                productTitle = "Dental Sticks",
                productDescription = "Fresh and crisp organic dental stick.",
                productPrice = "₱36.00",
                itemQuantity = "3 pcs",
                totalPrice = "₱108.00",
                deliveryDestination = "123 Elmo Street, Manila, BGC"
            ),
            TransactionReceive(
                profileImageRes = R.drawable.ic_person, // Replace with a valid drawable resource
                merchantStoreName = "Vetty Supplies",
                productTitle = "Ear Drops",
                productDescription = "Clean your pet's ears with ease",
                productPrice = "₱150.00",
                itemQuantity = "1 unit",
                totalPrice = "₱150.00",
                deliveryDestination = "45 Tech Park, San Isidro, CA"
            )
        )
        _transactions.value = dummyData
    }
}
