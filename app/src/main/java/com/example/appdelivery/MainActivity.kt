package com.example.appdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appdelivery.presentation.commons.ui.theme.AppDeliveryTheme
import com.example.appdelivery.presentation.navigation.AppNavGraph
import com.example.appdelivery.presentation.screens.login.LoginScreen
import com.example.appdelivery.presentation.screens.login.LoginViewModel
import com.example.appdelivery.presentation.screens.presentation.PresentationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val loginViewModel: LoginViewModel by viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppDeliveryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        padding = innerPadding,
                        loginViewModel = loginViewModel,
                    )
                }
            }
        }
    }
}