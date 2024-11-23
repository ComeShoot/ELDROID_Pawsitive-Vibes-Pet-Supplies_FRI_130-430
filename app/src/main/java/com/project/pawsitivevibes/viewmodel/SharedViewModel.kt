package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // MutableLiveData to store the selected role
    private val _selectedRole = MutableLiveData<String>()
    val selectedRole: LiveData<String> get() = _selectedRole

    // Function to set the selected role
    fun setSelectedRole(role: String) {
        _selectedRole.value = role
    }
}
