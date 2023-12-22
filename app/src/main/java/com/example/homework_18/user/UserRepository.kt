package com.example.homework_18.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework_18.network.Network
import com.example.homework_18.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class UserRepository {
    private val apiService = Network.create(ApiService::class.java)

    fun getUsers(coroutineScope: CoroutineScope): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }
}

