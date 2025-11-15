package com.example.miniproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miniproject2.ui.theme.MiniProject2Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext

import com.example.miniproject2.roomdb.UserViewModel
import com.example.miniproject2.roomdb.UserViewModelFactory
import com.example.miniproject2.retrofit.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProject2Theme {
                val context = LocalContext.current
                val viewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(context)
                )
                UserScreen(viewModel)
            }
        }
    }
}
