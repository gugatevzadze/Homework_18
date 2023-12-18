package com.example.homework_18.service

import com.example.homework_18.user.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): ApiResponse
}