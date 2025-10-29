// NOTE: commentaire d'intro
package com.example.projet1diiage.StarWars.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.projet1diiage.StarWars.ui.AddPersonViewModel
import com.example.projet1diiage.StarWars.ui.AddPlanetViewModel
import com.example.projet1diiage.StarWars.ui.ApiViewModel
import com.example.projet1diiage.StarWars.ui.LocalViewModel

// Module Koin du layer UI: déclare comment créer les ViewModel
val starWarsUiModule = module {
    // Koin sait injecter les dépendances attendues grâce à get()
    viewModel { AddPersonViewModel(dao = get()) }
    viewModel { AddPlanetViewModel(dao = get()) }
    viewModel { LocalViewModel(personDao = get(), planetDao = get()) }
    viewModel { ApiViewModel(repo = get()) }
}



