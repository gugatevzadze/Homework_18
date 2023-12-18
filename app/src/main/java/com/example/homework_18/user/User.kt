package com.example.homework_18.user

import com.squareup.moshi.Json

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)