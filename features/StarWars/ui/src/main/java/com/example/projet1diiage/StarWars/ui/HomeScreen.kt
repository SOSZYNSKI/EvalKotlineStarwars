// NOTE: commentaire d'intro
package com.example.projet1diiage.StarWars.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Écran d'accueil avec 2 entrées: API et Local (Room)
@Composable
fun HomeScreen(onApi: () -> Unit, onLocal: () -> Unit) {
    Scaffold { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onApi, modifier = Modifier.fillMaxWidth()) {
                Text("API (afficher données API)")
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = onLocal, modifier = Modifier.fillMaxWidth()) {
                Text("LOCAL (Room) : personnes & planètes")
            }
        }
    }
}




