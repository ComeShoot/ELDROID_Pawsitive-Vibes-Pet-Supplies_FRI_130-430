package com.project.pawsitivevibes.api

import com.project.pawsitivevibes.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun registerUser(@Body user: User): Call<User>

    @POST("login")
    fun loginUser(@Body user: User): Call<User>
}