package com.example.miniproject2.roomdb

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject2.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.miniproject2.roomdb.UserEntity

class UserViewModel(context: Context): ViewModel() {
    private val dao: UserDao = createUserDatabase(context).userDao()
    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadUsers()
        refreshUsersFromApi()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            dao.getAllUsers().collect() {
                userList -> _users.value = userList
            }
        }
    }

    private fun refreshUsersFromApi() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val usersFromApi = com.example.miniproject2.retrofit.RetrofitInstance.api.getUsers()
                val userEntities = usersFromApi.map { user ->
                    UserEntity (
                        id = user.id,
                        name = user.name,
                        username = user.username,
                        email = user.email,
                        phone = user.phone,
                        website = user.website
                    )
                }
                dao.insertUsers(userEntities)
            } catch (e: Exception) {
                _errorMessage.value = "No internet connection; showing cached users."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchUsers(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                dao.getAllUsers().collect() {
                    userList -> _users.value = userList
                }
            } else {
                dao.searchUsers(query).collect() {
                    userList -> _users.value = userList
                }
            }
        }
    }
}