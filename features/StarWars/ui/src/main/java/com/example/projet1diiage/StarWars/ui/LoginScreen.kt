package com.example.projet1diiage.StarWars.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onEnter: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Connexion") }) }) { pad ->
        Column(
            modifier = Modifier.fillMaxSize().padding(pad).padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenue !", style = MaterialTheme.typography.headlineSmall)
            Text("Cliquez sur Entrer pour continuer.")
            Button(onClick = onEnter, modifier = Modifier.padding(top = 16.dp)) {
                Text("Entrer")
            }
        }
    }
}
