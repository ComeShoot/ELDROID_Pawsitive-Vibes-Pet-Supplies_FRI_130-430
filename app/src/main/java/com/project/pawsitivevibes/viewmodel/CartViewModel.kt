package com.project.pawsitivevibes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.CartItem
import com.project.pawsitivevibes.model.Item
import com.project.pawsitivevibes.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

//    private val cartRepository = CartRepository()
//
//    private val _cartItems = MutableLiveData<List<CartItem>>()
//    val cartItems: LiveData<List<CartItem>> get() = _cartItems
//
//    // Fetch the cart items from the repository
//    fun getCartItems() {
//        viewModelScope.launch {
//            try {
//                val response = cartRepository.getCartItems()
//                if (response.isSuccessful) {
//                    _cartItems.value = response.body()?.cartItems ?: emptyList()
//                } else {
//                    Log.e("CartViewModel", "Failed to fetch cart items: ${response.errorBody()?.string()}")
//                }
//            } catch (e: Exception) {
//                Log.e("CartViewModel", "Error fetching cart items", e)
//            }
//        }
//    }
}
