package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.CartItem
import com.project.pawsitivevibes.model.Item

class CartViewModel : ViewModel() {

    // Create a list of dummy items
    private val _items = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>> get() = _items

    init {
        // Add some dummy items
        val dummyItems = listOf(
            CartItem("BUMI OFFICIAL", "Olive Shampoo", "For Dogs and Cats", "₱500.00", R.drawable.eye_drops, 1),
            CartItem("PET SHOP", "Dog Food", "Premium Quality", "₱300.00", R.drawable.magic_milk, 2)
        )
        _items.value = dummyItems
    }
}
