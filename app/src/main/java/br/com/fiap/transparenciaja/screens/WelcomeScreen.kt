package br.com.fiap.transparenciaja.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.transparenciaja.R
import br.com.fiap.transparenciaja.ui.theme.TextoPrincipal
import br.com.fiap.transparenciaja.ui.theme.TransparenciaJaTheme

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToSingUp: () -> Unit) {
    val backgroundRadialGradient = Brush.radialGradient(
        colors = listOf(Color(0xff244A7C), Color(0xff1B3A6B), Color(0xff0F2847)),
        radius = 1200f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundRadialGradient)
    ) {

        // --- 1. CONTEÚDO CENTRAL (Logo e Textos) ---
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_transparenciaja),
                contentDescription = "Logo do Verifique Já",
                modifier = Modifier.size(80.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Transparência Já",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fiscalize, acompanhe e exija.",
                    color = Color(0x99FFFFFF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        // --- 2. CONTEÚDO DA BASE (Botão e Créditos) ---
        Column(

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onNavigateToSingUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2ECC71),
                    contentColor = TextoPrincipal
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Começar",
                    modifier = Modifier.padding(vertical = 4.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Desenvolvido por Escola de Dados da FIAP",
                color = Color(0x66FFFFFF), // Branco bem sutil
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WelcomePreview() {
    TransparenciaJaTheme {
        WelcomeScreen(onNavigateToSingUp = {})
    }
}