package com.project.pawsitivevibes.viewmodel

import android.app.Application
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


    // LiveData to hold registration status message
    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> get() = _registerStatus

    // LiveData to hold login status message
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    // LiveData to track if login is successful
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    // Register a Customer
    // In AuthViewModel
    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    fun registerCustomer(user: User) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = userRepository.registerCustomer(user)
                if (response.isSuccessful) {
                    _registerStatus.value = response.body()?.message ?: "Customer registration successful"
                    _registrationSuccess.value = true // Notify success
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
                    _registerStatus.value = response.body()?.message ?: "Seller registration successful"
                    _registrationSuccess.value = true // Notify success
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



    // Inside AuthViewModel
    fun loginUser(user: UserLogin) {
        viewModelScope.launch {
            try {
                val response = userRepository.loginUser(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userRole.value = it.role
                        _loginSuccess.value = true
                        _loginStatus.value = "Login successful as ${it.role}"
                        Log.d("AuthViewModel", "User role set to: ${it.role}")
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





}
