package com.example.appdelivery.domain.entities.presentation

import com.example.appdelivery.R

class FakePresentation {
    val presentationItems = listOf(
        PresentationEntity(
            imageRes = R.drawable.presentation1,
            title = "Escoge tu comida favorita",
            description = "Explora nuestro menú y selecciona tus platillos favoritos."
        ),
        PresentationEntity(
            imageRes = R.drawable.presentation2,
            title = "Ten tu comida a tiempo",
            description = "Recibe tu pedido rápidamente y siempre a tiempo."
        ),
        PresentationEntity(
            imageRes = R.drawable.presentation3,
            title = "Sé el primero en disfrutar",
            description = "Disfruta de tu comida recién preparada y deliciosa."
        )
    )
}