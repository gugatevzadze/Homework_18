package com.example.homework_18.user

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    fun getUsers(lifecycleScope: LifecycleCoroutineScope): Flow<PagingData<User>> {
        return userRepository.getUsers(viewModelScope)
    }
}

