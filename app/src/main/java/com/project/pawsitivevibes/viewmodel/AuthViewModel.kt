package com.project.pawsitivevibes.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.Seller
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.repository.LoginResponse
import com.project.pawsitivevibes.repository.RegisterResponse
import com.project.pawsitivevibes.repository.UserRepository
import com.project.pawsitivevibes.view.LoginActivity
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository()

    // LiveData for registration and login status messages
    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> get() = _registerStatus

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    // LiveData to track if login is successful
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    // Register a Customer
    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    fun registerCustomer(user: User) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = userRepository.registerCustomer(user)
                if (response.isSuccessful) {
                    // Ensure RegisterResponse contains the appropriate fields (e.g., message)
                    _registerStatus.value = response.body()?.message ?: "Customer registration successful"
                    _registrationSuccess.value = true
                } else {
                    _registerStatus.value = "Customer registration failed: ${response.errorBody()?.string()}"
                    _registrationSuccess.value = false
                }
            } catch (e: Exception) {
                _registerStatus.value = "Error: ${e.message}"
                _registrationSuccess.value = false
            }
        }
    }

    // Register a Seller
    fun registerSeller(seller: Seller) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = userRepository.registerSeller(seller)
                if (response.isSuccessful) {
                    // Ensure RegisterResponse contains the appropriate fields (e.g., message)
                    _registerStatus.value = response.body()?.message ?: "Seller registration successful"
                    _registrationSuccess.value = true
                } else {
                    _registerStatus.value = "Seller registration failed: ${response.errorBody()?.string()}"
                    _registrationSuccess.value = false
                }
            } catch (e: Exception) {
                _registerStatus.value = "Error: ${e.message}"
                _registrationSuccess.value = false
            }
        }
    }

    // Login User
    fun loginUser(user: UserLogin) {
        viewModelScope.launch {
            try {
                val response = userRepository.loginUser(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val token = it.token
                        val sellerId = it.seller_id
                        val custId = it.cust_id?.toInt()

                        saveTokenAndSellerIdToSharedPreferences(token, sellerId)
                        if (custId != null) {
                            saveCustIdToPreferences(getApplication(), custId)
                        }

                        _userRole.value = it.role
                        _loginSuccess.value = true
                        _loginStatus.value = "Login successful as ${it.role}"
                        Log.d("AuthViewModel", "User role set to: ${it.role}")
                        Log.d("AuthViewModel", "Seller Id: ${it.seller_id}")
                    }
                } else {
                    _loginSuccess.value = false
                    _loginStatus.value = "Login failed: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _loginSuccess.value = false
                _loginStatus.value = "Error: ${e.message}"
            }
        }
    }

    // Save token and seller_id to SharedPreferences
    private fun saveTokenAndSellerIdToSharedPreferences(token: String?, sellerId: String?) {
        if (!token.isNullOrEmpty() && !sellerId.isNullOrEmpty()) {
            val sharedPreferences = getApplication<Application>().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("auth_token", "Bearer $token")  // Store token with "Bearer " prefix
            editor.putString("seller_id", sellerId)  // Save seller ID
            editor.apply()
            Log.d("AuthViewModel", "Token and seller_id saved to SharedPreferences")
        } else {
            Log.e("AuthViewModel", "Failed to save token or seller_id: One or both are null")
        }
    }

    // Save the customer ID to SharedPreferences
    fun saveCustIdToPreferences(context: Context, custId: Int) {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("cust_id", custId)
            apply()
        }
    }

    // Retrieve customer ID from SharedPreferences
    fun getCustIdFromPreferences(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("cust_id", -1)  // Default value -1 if not set
    }
}

