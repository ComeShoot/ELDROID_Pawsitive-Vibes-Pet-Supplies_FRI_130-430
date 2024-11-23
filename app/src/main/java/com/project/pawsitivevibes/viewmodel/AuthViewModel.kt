package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pawsitivevibes.model.User

class AuthViewModel : ViewModel() {

    // MutableLiveData for registering a user and login status
    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> get() = _registerStatus

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    // This function simulates a user registration process
    fun registerUser(user: User) {
        // This would be where you call a repository or use case to register the user
        // For now, it's just a dummy implementation to show how the ViewModel works
        if (user.email.isNotEmpty() && user.password.isNotEmpty() && user.role.isNotEmpty()) {
            // Simulating registration success
            _registerStatus.value = "Registration successful for ${user.role}"
        } else {
            // If any field is missing, set failure status
            _registerStatus.value = "Please provide valid details"
        }
    }

    // This function simulates user login
    fun loginUser(user: User) {
        // This would be where you call a repository or use case to log the user in
        // For now, we are just simulating a successful login with dummy data
        if (user.email == "test@example.com" && user.password == "password") {
            // Simulate a successful login
            _loginStatus.value = "Login successful"
        } else {
            // Simulate a failed login
            _loginStatus.value = "Invalid credentials"
        }
    }
}
