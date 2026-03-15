package br.com.fiap.transparenciaja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val darkBlue = Color(0xFF203E71)
    val lightGray = Color(0xFFF8F9FA)
    val orangeLimit = Color(0xFFF7A01B)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Transparência Já",
                        color = darkBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Search, contentDescription = null, tint = Color.Gray)
                    }
                    Box {
                        IconButton(onClick = {}) {
                            Icon(Icons.Outlined.Notifications, contentDescription = null, tint = Color.Gray)
                        }
                        // Bolinha vermelha de notificação
                        Box(
                            Modifier
                                .size(10.dp)
                                .offset(x = (-8).dp, y = 8.dp)
                                .background(Color.Red, CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = darkBlue, selectedTextColor = darkBlue)
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Buscar") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                    label = { Text("Alertas") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(lightGray)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Você acompanha", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                }
            }

            // Card Político 1
            item {
                PoliticoCard(
                    nome = "Ana Paula Silva",
                    cargo = "Deputada Federal",
                    partido = "PT",
                    uf = "SP",
                    gasto = "R$ 12.840",
                    porcentagem = 0.67f,
                    corBarra = darkBlue
                )
                Spacer(Modifier.height(16.dp))
            }

            // Card Político 2
            item {
                PoliticoCard(
                    nome = "Carlos Eduardo Lima",
                    cargo = "Senador",
                    partido = "MDB",
                    uf = "RJ",
                    gasto = "R$ 8.560",
                    porcentagem = 0.45f,
                    corBarra = darkBlue
                )
            }

            item {
                Row(
                    Modifier.fillMaxWidth().padding(top = 32.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Alertas de gastos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Ver todos", color = darkBlue, fontSize = 14.sp)
                }
            }

            // Lista de Alertas
            item { AlertaItem("Ana Paula Silva gastou R$ 4.200 em passagens", "hoje", Color.Red) }
            item { AlertaItem("Carlos Lima atingiu 80% do limite CEAP", "ontem", orangeLimit) }
            item { AlertaItem("Marcos Aurélio abaixo da média do mês", "2d atrás", Color.Green) }
        }
    }
}

@Composable
fun PoliticoCard(nome: String, cargo: String, partido: String, uf: String, gasto: String, porcentagem: Float, corBarra: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Placeholder para foto
                Box(Modifier.size(60.dp).clip(CircleShape).background(Color.LightGray))
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(cargo, color = Color.Gray, fontSize = 14.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(color = Color(0xFF203E71), shape = RoundedCornerShape(4.dp)) {
                            Text(partido, color = Color.White, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 12.sp)
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(uf, color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text("Gastou $gasto esse mês", color = Color(0xFFF7A01B), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { porcentagem },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                color = corBarra,
                trackColor = Color(0xFFF1F1F1)
            )
            Text("${(porcentagem * 100).toInt()}% do limite CEAP usado", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
fun AlertaItem(titulo: String, tempo: String, corBolinha: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(12.dp).background(corBolinha, CircleShape))
            Spacer(Modifier.width(16.dp))
            Column {
                Text(titulo, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text(tempo, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}