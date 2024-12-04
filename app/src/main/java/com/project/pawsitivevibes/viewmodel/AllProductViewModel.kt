package com.project.pawsitivevibes.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.AddToCartRequest
import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.model.CartItem
import com.project.pawsitivevibes.model.CartItemsRequest
import com.project.pawsitivevibes.repository.AllProductRepository
import kotlinx.coroutines.launch

class AllProductViewModel(application: Application) : AndroidViewModel(application) {
    private val _addToCartStatus = MutableLiveData<Boolean>()
    val addToCartStatus: LiveData<Boolean> get() = _addToCartStatus
    private val repository = AllProductRepository()
    private val _products = MutableLiveData<List<AllProduct>>()
    val products: LiveData<List<AllProduct>> get() = _products
    private val _cartItems = MutableLiveData<List<CartItem>?>()
    val cartItems: MutableLiveData<List<CartItem>?> get() = _cartItems
    init {
        fetchAllProducts()
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.getAllProducts()
                Log.d("ProductViewModel", "Fetched products: $productList")
                _products.postValue(productList)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products", e)
            }
        }
    }

    fun addToCart(prodId: Int, cartItemQuantity: Int, cartItemPrice: Double) {
        viewModelScope.launch {
            try {
                val custId = getCustIdFromPreferences()
                val request = AddToCartRequest(cust_id = custId, prod_id = prodId, cart_item_quantity = cartItemQuantity, cart_item_price = cartItemPrice)
                Log.d("AddToCartRequest", "Request: $request")
                val response = repository.addToCart(request)
                _addToCartStatus.postValue(response.isSuccessful)
            } catch (e: Exception) {
                _addToCartStatus.postValue(false)
                e.printStackTrace()
            }
        }
    }

    fun fetchCartItems() {
        viewModelScope.launch {
            try {
                val custId = getCustIdFromPreferences()
                val request = CartItemsRequest(cust_id = custId)

                // Perform the network call to fetch cart items
                val response = repository.getCartItems(request)

                // Check if the response is successful and body is not null
                if (response.isSuccessful) {
                    val cartItems = response.body()?.cartItems
                    if (cartItems != null) {
                        // Post the cart items to the LiveData
                        _cartItems.postValue(cartItems)
                    } else {
                        // Handle the case where the cartItems are null
                        Log.e("CartViewModel", "No cart items found")
                        _cartItems.postValue(emptyList()) // Post an empty list if no items are found
                    }
                } else {
                    // Handle unsuccessful response (e.g., show an error message)
                    Log.e("CartViewModel", "Error fetching cart items: ${response.message()}")
                    _cartItems.postValue(emptyList()) // Post an empty list on error
                }
            } catch (e: Exception) {
                // Handle exceptions and log them
                Log.e("CartViewModel", "Exception occurred while fetching cart items", e)
                _cartItems.postValue(emptyList()) // Post an empty list on error
            }
        }
    }


    private fun getCustIdFromPreferences(): Int {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("cust_id", -1) // Default value -1 if not found
    }
}

