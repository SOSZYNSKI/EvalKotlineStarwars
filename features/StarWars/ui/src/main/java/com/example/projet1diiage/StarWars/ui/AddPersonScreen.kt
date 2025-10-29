package com.example.projet1diiage.StarWars.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet1diiage.StarWars.data.local.dao.PersonDao
import com.example.projet1diiage.StarWars.data.local.entity.PersonEntity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

// AddpersonScren et le viewmodel qui gère ou plutôt organise l'affichage et l'appel/traitement des données
//(Reçu et envoyé) pour la créations d'un personnage en Room
class AddPersonViewModel(private val dao: PersonDao) : ViewModel()
{
    // déclarations des différentes variables en fonction des différents input que le user va interagir avec
    // En gros je déclare toutes les variables en avance comme # pour pouvoir rentrer les data dedans
    var id by mutableStateOf("")
    var name by mutableStateOf("")
    var birthYear by mutableStateOf("")
    var gender by mutableStateOf("")
    var height by mutableStateOf("")
    var mass by mutableStateOf("")
    var error by mutableStateOf<String?>(null) // Petite Variable de gestion d'erreur en cas de besoins
    var isSaving by mutableStateOf(false) // Pour le status, permet de savoir si
    // L'enregistrement est en cours

    // Function save qui permet de sauvegarder une Personne
    fun save(onDone: () -> Unit) {
        if (name.isBlank()) { // Je vérifie ici si il n'y a pas de nom pour faire la gestion des erreurs
            error = "Le nom est obligatoire."
            return
        }
        isSaving = true // Désactive les actions pendant la sauvegarde de la persopnne
        viewModelScope.launch {
            try
            {
                // Prépare l'entité à sauvegarder. Je génère un id si vide.
                val entity = PersonEntity(
                    id = id.ifBlank { System.currentTimeMillis().toString() }, // Ici "génération si vide"
                    name = name.trim(),
                    birthYear = birthYear.ifBlank { null }, // Champs optionnels => null si vide
                    gender = gender.ifBlank { null },
                    height = height.ifBlank { null },
                    mass = mass.ifBlank { null },
                    homeworldUrl = null
                )
                dao.upsert(entity) // Insert ou update dans la base locale
                onDone() // Remonte à l'écran appelant (fermeture/navigation)
            }
            catch (t: Throwable)
            {
                error = t.message ?: "Erreur inconnue." // renvoie l'erreur
            }
            finally
            {
                isSaving = false // Réactivation des boutons
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) //J'ajoute ça car c'est "expérimental"
@Composable // Pour jetpack compose, je crée un composable qui sera appelé dans le View pour être affiché
fun AddPersonScreen(onDone: () -> Unit, vm: AddPersonViewModel = koinViewModel())
{
    val scroll = rememberScrollState() // Permet de scroller le formulaire

    Scaffold(
        topBar = { TopAppBar(title = { Text("Ajouter une personne") }) } // Barre du haut avec le titre
    ) { pad ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(16.dp)
                .verticalScroll(scroll), // Active le scroll sur la colonne
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Demande un Id si possible
            OutlinedTextField(
                value = vm.id, onValueChange = { vm.id = it },
                label = { Text("ID (optionnel)") },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            // Nom requis (affiche une erreur si vide)
            OutlinedTextField(
                value = vm.name, onValueChange = { vm.name = it },
                label = { Text("Nom *") },
                singleLine = true, isError = vm.name.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )
            // Année de naissance du perso
            OutlinedTextField(
                value = vm.birthYear, onValueChange = { vm.birthYear = it },
                label = { Text("Année de naissance (ex: 19BBY)") },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            // Genre du perso
            OutlinedTextField(
                value = vm.gender, onValueChange = { vm.gender = it },
                label = { Text("Genre") },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            // Taille du perso en nombre
            OutlinedTextField(
                value = vm.height, onValueChange = { vm.height = it },
                label = { Text("Taille (cm)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Poids perso
            OutlinedTextField(
                value = vm.mass, onValueChange = { vm.mass = it },
                label = { Text("Poids (kg)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Affiche l'erreur sous les champs
            vm.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            // Boutons : annuler ou enregistrer
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onDone, enabled = !vm.isSaving) { Text("Annuler") }
                Button(onClick = { vm.save(onDone) }, enabled = !vm.isSaving) { // Désactivé pendant l'enregistrement
                    Text("Enregistrer")
                }
            }
        }
    }
}

