package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// SharedViewModel.kt
class SharedLoginViewModel : ViewModel() {
    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    fun setUserRole(role: String) {
        _userRole.value = role
    }
}
