package com.awatech.cuchatapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.screens.App
import com.awatech.cuchatapp.ui.theme.CuChaappTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userViewModel: UserViewModel = viewModel()
            val messageViewModel: MessageViewModel = viewModel()
            val navController = rememberNavController()
            CuChaappTheme {
                Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App(userViewModel = userViewModel, messageViewModel = messageViewModel, navController = navController)
                }
            }
        }
    }
}



