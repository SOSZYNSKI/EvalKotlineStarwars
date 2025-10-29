// Ecran de recherche API: je tape un nom et je demande au repo de me renvoyer des personnes\n
package com.example.projet1diiage.StarWars.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import android.media.AudioManager
import android.media.ToneGenerator

// ViewModel qui gère la recherche de personnes via l'API
class ApiViewModel(
    private val repo: StarWarsRepository
) : ViewModel() {
    var query by mutableStateOf("") // le texte tapé par l'utilisateur
    var results by mutableStateOf<List<Person>>(emptyList()) // Résultat de la recherche
    var loading by mutableStateOf(false) // Pour afficher un loader
    var error by mutableStateOf<String?>(null) // Message d'erreur éventuel

    fun search() {
        // Lance un appel réseau via le dépôt et met à jour l'UI
        viewModelScope.launch {
            loading = true; error = null
            try {
                results = repo.searchPeople(query, page = 1)
            } catch (t: Throwable) {
                error = t.message
            } finally {
                loading = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiScreen(onBack: () -> Unit, vm: ApiViewModel = koinViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Recherche API") }) // Titre de l'écran
        }
    ) { pad ->
        val tone = remember { ToneGenerator(AudioManager.STREAM_MUSIC, 80) } // Petit bip pour le bouton "Favoris"
        DisposableEffect(Unit) {
            onDispose { tone.release() }
        }

        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp)) {
            // Champ de recherche
            OutlinedTextField(
                value = vm.query,
                onValueChange = { vm.query = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nom contient...") }
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { vm.search() }) { Text("Chercher") } // Lance la requête
                OutlinedButton(onClick = { tone.startTone(ToneGenerator.TONE_PROP_BEEP, 150) }) { Text("Favoris") } // Bouton exemple
            }
            Spacer(Modifier.height(16.dp))

            if (vm.loading) CircularProgressIndicator() // Loader
            vm.error?.let { Text("Erreur: $it", color = MaterialTheme.colorScheme.error) } // Erreur

            // Liste des résultats
            LazyColumn {
                items(vm.results) { p ->
                    ListItem(
                        headlineContent = { Text(p.name) },
                        supportingContent = { Text("id=${p.id} | birthYear=${p.birthYear ?: "-"}") }
                    )
                    Divider()
                }
            }

            Spacer(Modifier.height(16.dp))
            OutlinedButton(onClick = onBack) { Text("Retour") } // Revient en arrière
        }
    }
}


