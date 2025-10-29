package com.example.projet1diiage

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.projet1diiage.StarWars.data.di.starWarsDataModule
import com.example.projet1diiage.StarWars.ui.di.starWarsUiModule // ViewModels de la feature

// Use cases sont instanciÃ©s ici (pour garder Domain pur)
import org.koin.dsl.module
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository
import com.example.projet1diiage.StarWars.domain.usecase.*

val starWarsUseCasesModule = module {
    factory { GetPersonUseCase(get<StarWarsRepository>()) }
    factory { SearchPeopleUseCase(get<StarWarsRepository>()) }
    factory { GetPlanetUseCase(get<StarWarsRepository>()) }
}

// Application Android où l'on démarre Koin et on enregistre les modules
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App) // Passe le context Android à Koin
            modules(
                starWarsDataModule,
                starWarsUiModule // ViewModels de la feature
            )
        }
    }
}
