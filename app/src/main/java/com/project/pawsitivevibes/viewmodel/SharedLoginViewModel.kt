package com.project.pawsitivevibes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// SharedViewModel.kt
class SharedLoginViewModel : ViewModel() {
    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    // In SharedLoginViewModel
    fun setUserRole(role: String) {
        Log.d("SharedLoginViewModel", "Setting user role: $role")
        _userRole.postValue(role)  // Notify observers immediately
    }


}
