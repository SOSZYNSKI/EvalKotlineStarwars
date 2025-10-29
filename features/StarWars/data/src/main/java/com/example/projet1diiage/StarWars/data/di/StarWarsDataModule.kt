package com.example.projet1diiage.StarWars.data.di


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.projet1diiage.StarWars.data.StarWarsRepositoryImpl
import com.example.projet1diiage.StarWars.data.remote.SwapiService
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository

import org.koin.android.ext.koin.androidContext
import com.example.projet1diiage.StarWars.data.local.StarWarsDatabase

// Module Koin du layer Data: API Retrofit, DB Room, DAOs et Repository
// Ici je dis à Koin comment créer toutes les briques du layer Data:
// Moshi (JSON), OkHttp (HTTP), Retrofit (API), la DB Room + DAOs, et enfin le Repository.
// L'UI (ou les ViewModels) pourront demander ces dépendances sans les instancier à la main.

val starWarsDataModule = module {
    // Moshi convertit le JSON en objets Kotlin
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
    // client HTTP "logs basiques en debug"
    single {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        OkHttpClient.Builder().addInterceptor(logging).build()
    }

    // Retrofit configurer avec : "baseUrl" de SWAPI et Moshi
    single {
        Retrofit.Builder()
            .baseUrl("https://www.swapi.tech/api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single<SwapiService> { get<Retrofit>().create(SwapiService::class.java) }

    // Koin du : Database + DAOs (accès local aux données)
    single { StarWarsDatabase.build(androidContext()) }
    single { get<StarWarsDatabase>().personDao() }
    single { get<StarWarsDatabase>().planetDao() }

    // L'implémentation du dépôt (API) exposée via l'interface du domaine
    single<StarWarsRepository> { StarWarsRepositoryImpl(api = get()) }
}
