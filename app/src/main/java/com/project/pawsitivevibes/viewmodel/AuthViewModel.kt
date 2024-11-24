package com.project.pawsitivevibes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.repository.LoginResponse
import com.project.pawsitivevibes.repository.RegisterResponse
import com.project.pawsitivevibes.repository.UserRepository
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

    // Register a user
    fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = userRepository.registerUser(user)
                if (response.isSuccessful) {
                    // Update register status with success message
                    _registerStatus.value = response.body()?.message ?: "Registration successful"
                } else {
                    // If registration fails, show error message
                    _registerStatus.value = "Registration failed: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                // Handle error during registration
                _registerStatus.value = "Error: ${e.message}"
            }
        }
    }

    // Login a user
    fun loginUser(user: UserLogin) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = userRepository.loginUser(user)
                if (response.isSuccessful) {
                    // On successful login, update success status
                    _loginSuccess.value = true
                    // Also, update the login status with the token
                    _loginStatus.value = "Login successful. Token: ${response.body()?.token}"
                } else {
                    // If login fails, update status with error message
                    _loginSuccess.value = false
                    _loginStatus.value = "Login failed: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                // Handle error during login
                _loginSuccess.value = false
                _loginStatus.value = "Error: ${e.message}"
            }
        }
    }
}
