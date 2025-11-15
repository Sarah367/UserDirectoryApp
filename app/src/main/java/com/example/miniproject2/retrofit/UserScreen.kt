package com.example.miniproject2.retrofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.miniproject2.roomdb.UserViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val searchQuery = remember { mutableStateOf("")}
        TextField(
            value = searchQuery.value,
            onValueChange = { newValue ->
                searchQuery.value = newValue
                viewModel.searchUsers(newValue)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search by name or email")}
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
        LazyColumn {
            items(users) { user ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(user.id.toString())
                        Text(user.name)
                        Text(user.email)
                        Text(user.phone)
                    }
                }
                Spacer(Modifier.padding(8.dp))
            }
        }
    }
}

