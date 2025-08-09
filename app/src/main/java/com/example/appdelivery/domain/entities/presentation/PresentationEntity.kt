package com.example.appdelivery.domain.entities.presentation

data class PresentationEntity (
    val imageRes: Int,
    val imageDescription: String? = null,
    val title: String,
    val description: String,
)

