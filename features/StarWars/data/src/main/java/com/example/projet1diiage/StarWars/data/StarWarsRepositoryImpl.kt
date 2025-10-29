package com.example.projet1diiage.StarWars.data


import com.example.projet1diiage.StarWars.data.mapper.*
import com.example.projet1diiage.StarWars.data.remote.SwapiService
import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.model.Planet
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository

// Ici je crée l'implémentation du repository qui parle avec l'API.
// En gros: l'interface du domaine (StarWarsRepository) est le plan à respecter,
// et cette classe (RepoImpl) suit ce plan en allant chercher les donnés via Retrofit,
// puis je converti (map) les DTO réseaux vers les modèles du domaine.
// Implémentation du dépôt qui utilise l'API distante (Retrofit)

class StarWarsRepositoryImpl(
    private val api: SwapiService
) : StarWarsRepository {

    // Récupère une personne par id via l'API puis mappe vers le modèle domaine
    override suspend fun getPerson(id: String): Person =
        api.getPerson(id).toDomain()

    // Recherche des personnes (la réponse API renvoie une liste "résumée")
    override suspend fun searchPeople(query: String, page: Int): List<Person> =
        api.searchPeople(query, page).results.map { it.toPersonDomain() }

    // Récupère une planète par id via l'API
    override suspend fun getPlanet(id: String): Planet =
        api.getPlanet(id).toDomain()
}
