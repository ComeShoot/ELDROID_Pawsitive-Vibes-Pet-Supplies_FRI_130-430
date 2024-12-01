package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.repository.ForgotPasswordRepository
import com.project.pawsitivevibes.repository.ForgotPasswordResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ForgotPasswordViewModel(private val repository: ForgotPasswordRepository) : ViewModel() {

    private val _emailVerificationResponse = MutableLiveData<Response<ForgotPasswordResponse>>()
    val emailVerificationResponse: LiveData<Response<ForgotPasswordResponse>> = _emailVerificationResponse

    private val _passwordResetResponse = MutableLiveData<Response<ForgotPasswordResponse>>()
    val passwordResetResponse: LiveData<Response<ForgotPasswordResponse>> = _passwordResetResponse

    fun verifyEmail(email: String) {
        viewModelScope.launch {
            try {
                val response = repository.verifyEmail(email)
                if (response.isSuccessful) {
                    _emailVerificationResponse.postValue(response)
                } else {
                    // Handle error response from the server
                    _emailVerificationResponse.postValue(Response.error(response.code(), response.errorBody()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally, post an error response or a specific status
            }
        }
    }

    fun resetPassword(request: ForgotPasswordRequest) {
        viewModelScope.launch {
            try {
                val response = repository.resetPassword(request)
                if (response.isSuccessful) {
                    _passwordResetResponse.postValue(response)
                } else {
                    _passwordResetResponse.postValue(Response.error(response.code(), response.errorBody()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
