// NOTE: commentaire d'intro
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
import com.example.projet1diiage.StarWars.data.local.dao.PersonDao
import com.example.projet1diiage.StarWars.data.local.dao.PlanetDao
import com.example.projet1diiage.StarWars.data.local.entity.PersonEntity
import com.example.projet1diiage.StarWars.data.local.entity.PlanetEntity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlinx.coroutines.flow.collectLatest

// ViewModel qui expose les listes locales (Room)
class LocalViewModel(
    private val personDao: PersonDao,
    private val planetDao: PlanetDao
) : ViewModel() {
    var people by mutableStateOf<List<PersonEntity>>(emptyList()) // liste des personnes en base
    var planets by mutableStateOf<List<PlanetEntity>>(emptyList()) // liste des planètes en base

    fun refresh() {
        // Recharge les données une fois (utilisé au démarrage de l'écran)
        viewModelScope.launch {
            people = personDao.getAll()
            planets = planetDao.getAll()
        }
    }

    init {
        // Observe les changements en base et met à jour l'UI automatiquement
        viewModelScope.launch {
            personDao.observeAll().collectLatest { people = it }
        }
        viewModelScope.launch {
            planetDao.observeAll().collectLatest { planets = it }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Écran liste qui affiche People et Planets depuis Room
fun LocalListScreen(
    onAddPerson: () -> Unit,
    onAddPlanet: () -> Unit,
    onBack: () -> Unit,
    vm: LocalViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) { vm.refresh() } // charge une première fois

    Scaffold(
        topBar = { TopAppBar(title = { Text("Local (Room)") }) },
        floatingActionButton = {
            Column {
                ExtendedFloatingActionButton(onClick = onAddPerson) { Text("Ajouter une personne") }
                Spacer(Modifier.height(12.dp))
                ExtendedFloatingActionButton(onClick = onAddPlanet) { Text("Ajouter une planète") }
            }
        }
    ) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp)) {
            Text("Personnes", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(vm.people) { p ->
                    ListItem(
                        headlineContent = { Text(p.name) },
                        supportingContent = { Text("id=${p.id} â€¢ ${p.birthYear ?: "-"}") }
                    )
                    Divider()
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Planètes", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(vm.planets) { pl ->
                    ListItem(
                        headlineContent = { Text(pl.name) },
                        supportingContent = { Text("id=${pl.id} â€¢ pop=${pl.population ?: "-"}") }
                    )
                    Divider()
                }
            }

            Spacer(Modifier.height(16.dp))
            OutlinedButton(onClick = { vm.refresh() }) { Text("Rafraîchir") }

            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onBack) { Text("Retour") }
        }
    }
}





