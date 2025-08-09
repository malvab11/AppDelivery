package com.example.appdelivery.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appdelivery.presentation.screens.login.LoginScreen
import com.example.appdelivery.presentation.screens.presentation.PresentationScreen

/**
 * Define el grafo de navegación principal de la aplicación.
 *
 * Esta función configura las rutas y pantallas accesibles dentro de la app
 * utilizando Jetpack Compose Navigation.
 *
 * @param navController Controlador de navegación utilizado para manejar los cambios de pantalla.
 * @param padding Valores de padding que se aplicarán a cada pantalla, normalmente provenientes de un Scaffold.
 *
 * Flujo de navegación:
 * - [NavRoutes.Presentation]: Pantalla de presentación o bienvenida, primera en mostrarse al abrir la app.
 * - [NavRoutes.Login]: Pantalla de inicio de sesión, a la cual se navega desde la presentación.
 *
 * Ejemplo de uso:
 * ```
 * val navController = rememberNavController()
 * AppNavGraph(navController = navController, padding = innerPadding)
 * ```
 */
@Composable
fun AppNavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Presentation
    ) {
        composable(NavRoutes.Presentation) {
            PresentationScreen(
                padding = padding,
                onNavigationClick = { navController.navigate(NavRoutes.Login) }
            )
        }
        composable(NavRoutes.Login) {
            LoginScreen(padding = padding)
        }
    }
}
