package com.example.projet1diiage.StarWars.domain.repository


import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.model.Planet

// Ici je défini le "contrat" (plan) du dépôt: quelles fonctions l'UI / use cases peuvent appeler.
// L'idée: peu importe si ça vient du réseau ou de Room, l'implémentation devra respecter ces signatures.
// Du coup on peut changer la source de données sans casser le reste du code.
interface StarWarsRepository {
    suspend fun getPerson(id: String): Person
    suspend fun searchPeople(query: String, page: Int = 1): List<Person>
    suspend fun getPlanet(id: String): Planet
}
