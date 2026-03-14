package br.com.fiap.transparenciaja.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.transparenciaja.screens.LoginScreen
import br.com.fiap.transparenciaja.screens.SingUpScreen
import br.com.fiap.transparenciaja.screens.WelcomeScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onNavigateToSingUp = { navController.navigate("singup") }
            )
        }

        composable("singup") {
            SingUpScreen(onNavigateBack = {navController.popBackStack()}, onNavigateToLogin = {navController.navigate("login")})
        }

        composable("login") {
            LoginScreen(onNavigateBack = {navController.popBackStack()}, onNavigateToSingUp = {navController.navigate("singup")})
        }
    }
}