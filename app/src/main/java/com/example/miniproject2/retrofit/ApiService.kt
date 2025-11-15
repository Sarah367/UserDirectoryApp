package com.example.miniproject2.retrofit

import com.example.miniproject2.retrofit.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}