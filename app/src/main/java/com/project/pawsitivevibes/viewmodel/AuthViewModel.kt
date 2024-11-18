package com.project.pawsitivevibes.viewmodel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.pawsitivevibes.api.ApiClient
import com.project.pawsitivevibes.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> get() = _registerStatus

    fun registerUser(user: User) {
        ApiClient.apiService.registerUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _registerStatus.postValue("Registration successful")
                } else {
                    _registerStatus.postValue("Registration failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _registerStatus.postValue("Error: ${t.message}")
            }
        })
    }

    fun loginUser(user: User) {
        ApiClient.apiService.loginUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _loginStatus.postValue("Login successful")
                } else {
                    _loginStatus.postValue("Login failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _loginStatus.postValue("Error: ${t.message}")
            }
        })
    }
}