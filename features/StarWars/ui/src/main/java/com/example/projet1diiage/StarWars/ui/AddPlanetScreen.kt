// NOTE: commentaire d'intro
package com.example.projet1diiage.StarWars.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet1diiage.StarWars.data.local.dao.PlanetDao
import com.example.projet1diiage.StarWars.data.local.entity.PlanetEntity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

// ViewModel pour gérer l'ajout d'une planète
class AddPlanetViewModel( private val dao: PlanetDao) : ViewModel()
{
    var id by mutableStateOf("")
    var name by mutableStateOf("")
    var climate by mutableStateOf("")
    var diameter by mutableStateOf("")
    var gravity by mutableStateOf("")
    var population by mutableStateOf("")
    var error by mutableStateOf<String?>(null) // message d'erreur
    var isSaving by mutableStateOf(false) // pour désactiver les boutons pendant la sauvegarde

    fun save(onDone: () -> Unit) {
        // Validation simple: nom requis
        if (name.isBlank()) {
            error = "Le nom est obligatoire."
            return
        }
        isSaving = true // évite les doubles clics
        viewModelScope.launch {
            try {
                val entity = PlanetEntity(
                    id = id.ifBlank { System.currentTimeMillis().toString() },
                    name = name.trim(),
                    climate = climate.ifBlank { null },
                    diameter = diameter.ifBlank { null },
                    gravity = gravity.ifBlank { null },
                    population = population.ifBlank { null },
                )
                dao.upsert(entity) // insert/update en base
                onDone() // ferme l'écran
            } catch (t: Throwable) {
                error = t.message ?: "Erreur inconnue."
            } finally {
                isSaving = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Écran Compose pour saisir une planète
fun AddPlanetScreen(
    onDone: () -> Unit,
    vm: AddPlanetViewModel = koinViewModel()
) {
    val scroll = rememberScrollState() // permet de scroller si beaucoup de champs
    Scaffold(topBar = { TopAppBar(title = { Text("Ajouter une planète") }) }) { pad ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(16.dp)
                .verticalScroll(scroll), // active le scroll
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Champs du formulaire (id optionnel)
            OutlinedTextField(value = vm.id, onValueChange = { vm.id = it }, label = { Text("ID (optionnel)") }, singleLine = true)
            OutlinedTextField(value = vm.name, onValueChange = { vm.name = it }, label = { Text("Nom *") }, singleLine = true)
            OutlinedTextField(value = vm.climate, onValueChange = { vm.climate = it }, label = { Text("Climat") }, singleLine = true)
            OutlinedTextField(value = vm.diameter, onValueChange = { vm.diameter = it }, label = { Text("Diamètre") }, singleLine = true)
            OutlinedTextField(value = vm.gravity, onValueChange = { vm.gravity = it }, label = { Text("Gravité") }, singleLine = true)
            OutlinedTextField(value = vm.population, onValueChange = { vm.population = it }, label = { Text("Population") }, singleLine = true)

            vm.error?.let { Text(it, color = androidx.compose.material3.MaterialTheme.colorScheme.error) }

            androidx.compose.foundation.layout.Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onDone, enabled = !vm.isSaving) { Text("Annuler") }
                Button(onClick = { vm.save(onDone) }, enabled = !vm.isSaving) { Text("Enregistrer") }
            }
        }
    }
}



