package com.example.appdelivery.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(modifier: Modifier = Modifier, padding: PaddingValues) {
    Scaffold(modifier = Modifier.padding(paddingValues = padding)) {
        Column(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
            Text(text = "Hola Mundo")
        }
    }
}