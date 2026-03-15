package br.com.fiap.transparenciaja.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerifyEmailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color(0xFF203E71)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Verifique seu e-mail",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF203E71)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enviamos um link de acesso para ana@email.com. Clique no link para entrar.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Ação */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF203E71))
        ) {
            Text("Abrir app de e-mail", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Reenviar link · 00:41",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "E-mail errado? Voltar",
            modifier = Modifier.clickable { /* Ação voltar */ },
            textDecoration = TextDecoration.Underline,
            color = Color(0xFF203E71)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailPreview() {
    VerifyEmailScreen()
}