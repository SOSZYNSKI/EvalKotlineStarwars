// Ecran racine de navigation pour la feature: je décris les routes et où aller
package com.example.projet1diiage.StarWars.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Routes de navigation qui seront utilisé par le nav host
// En gros la page login, puis ensuite une autre page "segment"  qui affiche deux boutons qui permettrons
// Lors que leurs appel sur le même segment de changer le module "Composable"
object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val API = "api"
    const val LOCAL_LIST = "local_list"
    const val ADD_PERSON = "add_person"
    const val ADD_PLANET = "add_planet"
}

// Déclare la navigation Compose pour la feature StarWars
@Composable
fun StarWarsNavHost(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginScreen(onEnter = { nav.navigate(Routes.HOME) { popUpTo(Routes.LOGIN) { inclusive = true } } }) }
        composable(Routes.HOME) { HomeScreen(
            onApi = { nav.navigate(Routes.API) },
            onLocal = { nav.navigate(Routes.LOCAL_LIST) }
        ) }
        composable(Routes.API) { ApiScreen(onBack = { nav.popBackStack() }) }
        composable(Routes.LOCAL_LIST) { LocalListScreen(
            onAddPerson = { nav.navigate(Routes.ADD_PERSON) },
            onAddPlanet = { nav.navigate(Routes.ADD_PLANET) },
            onBack = { nav.popBackStack() }
        ) }
        composable(Routes.ADD_PERSON) { AddPersonScreen(onDone = { nav.popBackStack() }) }
        composable(Routes.ADD_PLANET) { AddPlanetScreen(onDone = { nav.popBackStack() }) }
    }
}


