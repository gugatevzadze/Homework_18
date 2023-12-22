package com.example.homework_18.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework_18.network.Network
import com.example.homework_18.service.ApiService
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {
    private val apiService = Network.create(ApiService::class.java)

    //creating a Pager with specified configuration and a UserPagingSource factory
    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow.cachedIn(viewModelScope)
        //converting the Pager to a Flow of PagingData and caching the result in the ViewModel's scope
    }
}


