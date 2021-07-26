package com.example.listing_planets_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listing_planets_compose.models.Planets
import com.example.listing_planets_compose.ui.theme.Listing_Planets_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Listing_Planets_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val planets = listOf<Planets>(
                        Planets(
                            "Mercurio",
                            "Mercúrio é o menor e mais interno planeta do Sistema Solar, orbitando o Sol a cada 87,969 dias terrestres. A sua órbita tem a maior excentricidade e o seu eixo apresenta a menor inclinação em relação ao plano da órbita dentre todos os planetas do Sistema Solar.",
                            R.drawable.mercurio,
                        ),
                        Planets(
                            "Venus",
                            "Vénus ou Vênus é o segundo planeta do Sistema Solar em ordem de distância a partir do Sol, orbitando-o a cada 224,7 dias. Recebeu seu nome em homenagem à deusa romana do amor e da beleza Vénus, equivalente a Afrodite.",
                            R.drawable.venus
                        ),
                        Planets(
                            "Terra",
                            "A Terra é o terceiro planeta mais próximo do Sol, o mais denso e o quinto maior dos oito planetas do Sistema Solar. É também o maior dos quatro planetas telúricos. É por vezes designada como Mundo ou Planeta Azul.",
                            R.drawable.terra
                        ),
                        Planets(
                            "Marte",
                            "Marte é o quarto planeta a partir do Sol, o segundo menor do Sistema Solar. Batizado em homenagem ao deus romano da guerra, muitas vezes é descrito como o \"Planeta Vermelho\", porque o óxido de ferro predominante em sua superfície lhe dá uma aparência avermelhada.",
                            R.drawable.marte
                        ),
                        Planets(
                            "Jupter",
                            "Júpiter é o maior planeta do Sistema Solar, tanto em diâmetro quanto em massa, e é o quinto mais próximo do Sol. Possui menos de um milésimo da massa solar, contudo tem 2,5 vezes a massa de todos os outros planetas em conjunto. É um planeta gasoso, junto com Saturno, Urano e Netuno.",
                            R.drawable.jupter
                        ),
                        Planets(
                            "Saturno",
                            "Saturno é o sexto planeta a partir do Sol e o segundo maior do Sistema Solar atrás de Júpiter. Pertencente ao grupo dos gigantes gasosos, possui cerca de 95 massas terrestres e orbita a uma distância média de 9,5 unidades astronômicas.",
                            R.drawable.saturno
                        ),
                        Planets(
                            "Urano",
                            "Urano é o sétimo planeta a partir do Sol, o terceiro maior e o quarto mais massivo dos oito planetas do Sistema Solar. Foi nomeado em homenagem ao deus grego do céu, Urano, o pai de Cronos e o avô de Zeus.",
                            R.drawable.urano
                        ),
                        Planets(
                            "Netuno",
                            "Netuno ou Neptuno é o oitavo planeta do Sistema Solar, o último a partir do Sol desde a reclassificação de Plutão para a categoria de planeta anão, em 2006. Pertencente ao grupo dos gigantes gasosos, possui um tamanho ligeiramente menor que o de Urano, mas maior massa, equivalente a 17 massas terrestres.",
                            R.drawable.netuno
                        ),
                    )

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                contentColor = Color.White, backgroundColor = Color(0x770000ff),
                                title = { Text(text = "Aplicativo de Planetas") })
                        })
                    {
                        Navigation(planets = planets)
                    }
                }

            }
        }
    }
}

@Composable
fun Navigation(planets: List<Planets>) {
    val navigation = rememberNavController()
    NavHost(navController = navigation, startDestination = "HomeScreen") {
        composable("HomeScreen") { Screen(navigation, planets)}
        composable("screenDetails") {
            val planet = navigation.previousBackStackEntry?.arguments?.getParcelable<Planets>("planet")

            planet?.let {
                ScreenDetails(navController = navigation, planets = planet)
            }
        }
    }
}

@Composable
fun Screen(navController: NavController, planets: List<Planets>) {
    LazyColumn() {
        itemsIndexed(planets) {
            _, planet ->
            mCard(navController ,planets = planet)
        }
    }
}

@Composable
fun mCard(navController: NavController ,planets: Planets) {
    Card(elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, color = Color(0x77f5f5f5)),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(5.dp)
            .clickable {
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable("planet", planets)
                }
                navController.navigate("screenDetails")
            }
    ) {
            Row(
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(painter = painterResource(id = planets.Image), 
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Clique aqui para mais informações", textAlign = TextAlign.Center)
             }
        }
    }
}