package com.example.homework_18.user

import com.example.homework_18.user.User

data class ApiResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)