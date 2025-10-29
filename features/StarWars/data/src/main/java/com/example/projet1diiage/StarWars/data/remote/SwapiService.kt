package com.example.projet1diiage.StarWars.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Ici je d'écris avec Retrofit les routes SWAPI que je vais appeler.
// Pourquoi Retrofit ? car ça permet d'écrire une interface simple et il gère le http derrière
// Important: pour les détails, SWAPI renvoie un "wrapper" avec result.properties (il faut mapper après).
interface SwapiService {
    // Détail d'une personne par id (uid dans l'URL SWAPI)
    // Je récup un wrapper que je converti ensuite en modèle du domaine via un mapper.
    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id: String): SwapiItemDto<PersonPropertiesDto>

    // Recherche toutes les personnes (liste). On peut passer un texte (search) et une page.
    // La réponse contient "results" avec (name + url). L'id est à la fin de l'URL.
    @GET("people")
    suspend fun searchPeople(
        @Query("search") query: String,
        @Query("page") page: Int = 1
    ): SwapiSearchDto<SwapiBriefDto>

    // Renvoie une planète spécifique en fonction de son ID
    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id: String): SwapiItemDto<PlanetPropertiesDto>
}

