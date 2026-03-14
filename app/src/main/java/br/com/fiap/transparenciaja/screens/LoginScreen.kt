package br.com.fiap.transparenciaja.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.transparenciaja.components.TextBoxInput
import br.com.fiap.transparenciaja.logic.AuthRepository
import br.com.fiap.transparenciaja.ui.theme.BackgroundApp
import br.com.fiap.transparenciaja.ui.theme.ButtonPrimary
import br.com.fiap.transparenciaja.ui.theme.TextoPrincipal
import br.com.fiap.transparenciaja.ui.theme.TextoSecundario
import br.com.fiap.transparenciaja.ui.theme.TransparenciaJaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateToSingUp: () -> Unit
) {


    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    var mensagemFeedback by remember { mutableStateOf("") }
    var isErro by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundApp,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = TextoPrincipal
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Entrar",
                    color = TextoPrincipal,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Entre para continuar monitorando seus políticos e receber alertas.",
                    color = TextoSecundario,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                TextBoxInput(
                    label = "E-mail",
                    placeholder = "seu@email.com",
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                TextBoxInput(
                    label = "Senha",
                    placeholder = "Digite sua senha",
                    value = senha,
                    onValueChange = { senha = it },
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                if (mensagemFeedback.isNotEmpty()) {
                    Text(
                        text = mensagemFeedback,
                        color = if (isErro) Color(0xFFE74C3C) else Color(0xFF2ECC71),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Button(
                    onClick = {
                        val resultado = AuthRepository.loginValidation(email, senha)

                        val sucesso = resultado.first
                        val mensagem = resultado.second

                        mensagemFeedback = mensagem
                        isErro = !sucesso

                        if (sucesso) {
                            email = ""
                            senha = ""

                            // ex: onNavigateToHome()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonPrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Entrar",
                        modifier = Modifier.padding(vertical = 4.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = DividerDefaults.Thickness,
                    color = Color(0xFFD1D5DC)
                )

                Text(
                    text = "ou",
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = DividerDefaults.Thickness,
                    color = Color(0xFFD1D5DC)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Não tem conta? ",
                    color = Color(0xFF6B7280),
                    fontSize = 16.sp
                )

                Text(
                    text = "Criar conta",
                    color = Color(0xFF1B3A6B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onNavigateToSingUp()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    TransparenciaJaTheme {
        LoginScreen(onNavigateBack = {}, onNavigateToSingUp = {})
    }
}