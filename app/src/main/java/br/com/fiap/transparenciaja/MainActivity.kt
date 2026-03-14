package br.com.fiap.transparenciaja

import br.com.fiap.transparenciaja.ui.theme.TransparenciaJaTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.transparenciaja.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransparenciaJaTheme {
                AppNavigation()
            }
        }
    }
}

