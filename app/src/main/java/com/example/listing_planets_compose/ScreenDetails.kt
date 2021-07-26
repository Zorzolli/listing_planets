package com.example.listing_planets_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listing_planets_compose.models.Planets

@Composable
fun ScreenDetails(navController: NavController, planets: Planets) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp))
    Column() {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(painter = painterResource(id = planets.Image),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = planets.Name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = planets.Description,
                    style = TextStyle(fontSize = 12.sp),
                    textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Voltar")
        }
    }
}