package com.example.appdelivery.presentation.screens.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdelivery.domain.entities.presentation.FakePresentation
import com.example.appdelivery.domain.entities.presentation.PresentationEntity
import kotlinx.coroutines.delay

/**
 * Pantalla de presentación (Onboarding) que muestra imágenes, títulos y descripciones
 * con animaciones automáticas y un indicador visual de progreso.
 */
@Composable
fun PresentationScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onNavigationClick: () -> Unit
) {
    // Estado que guarda el índice actual de la presentación
    var currentIndex by remember { mutableIntStateOf(0) }

    // Lista de elementos de presentación obtenidos de una clase fake/mock
    val items = FakePresentation().presentationItems

    // Efecto lanzado una sola vez que cambia de elemento cada 5 segundos
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentIndex = (currentIndex + 1) % items.size
        }
    }

    // Contenedor principal
    Column(
        modifier = modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animación entre imágenes (deslizar horizontal + fade)
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                (slideInHorizontally { width -> width } + fadeIn())
                    .togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
            },
            label = "Slide Animation",
            modifier = Modifier.weight(1f) // Ocupa la parte superior
        ) { it ->
            PresentationImage(
                modifier = Modifier.fillMaxSize(),
                imageRes = items[it]
            )
        }

        // Contenido textual e indicadores
        PresentationContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            item = items[currentIndex],
            size = items.size,
            currentIndex = currentIndex
        ) {onNavigationClick()}
    }
}

/**
 * Muestra la imagen de presentación con esquinas redondeadas y fondo.
 */
@Composable
private fun PresentationImage(modifier: Modifier = Modifier, imageRes: PresentationEntity) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes.imageRes),
            contentDescription = imageRes.imageDescription, // Descripción: Imagen decorativa
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Muestra el título, descripción, indicadores de progreso y un botón de acción.
 *
 * @param item Elemento actual de la presentación
 * @param size Número total de elementos
 * @param currentIndex Índice actual para marcar el indicador activo
 * @param onNavigationClick Funcion para la navegación de Presentation -> Login
 */
@Composable
private fun PresentationContent(
    modifier: Modifier = Modifier,
    item: PresentationEntity,
    size: Int,
    currentIndex: Int,
    onNavigationClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título principal
        Text(
            text = item.title,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        // Descripción
        Text(
            text = item.description,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        // Indicadores de progreso (puntos)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 8.dp),
        ) {
            items(size) { it ->
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(shape = CircleShape)
                        .background(if (it == currentIndex) Color.Black else Color.Gray)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        // Botón para continuar
        Button(
            onClick = onNavigationClick, // Navegación a pantalla de Login
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(text = "Siguiente", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}
