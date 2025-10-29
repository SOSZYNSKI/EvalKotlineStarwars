package com.example.projet1diiage.StarWars.data


import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.model.Planet
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository
import com.example.projet1diiage.StarWars.data.local.dao.PersonDao
import com.example.projet1diiage.StarWars.data.local.dao.PlanetDao
import com.example.projet1diiage.StarWars.data.mapper.toDomain

// Implémentation du dépôt qui lit/écrit en locale "Room"
// Ici c'est la version "locale" du repository: je lis/écris en Room.
// Même idée: on respecte le plan (interface) du domaine pour garantir les méthodes disponibles,
// mais la source vient de la base locale au lieu du réseau.

class StarWarsRepositoryRoomImpl(
    private val personDao: PersonDao,
    private val planetDao: PlanetDao
) : StarWarsRepository {

    override suspend fun getPerson(id: String): Person {
        // Va chercher l'entité en base (ou lève une erreur si absente)
        val entity = personDao.getById(id)
            ?: error("Person not found locally (id=$id)")
        return entity.toDomain()
    }

    override suspend fun searchPeople(query: String, page: Int): List<Person> {
        // page ignorée en local: on renvoie le filtrage simple
        return personDao.searchByName(query).map { it.toDomain() }
    }

    override suspend fun getPlanet(id: String): Planet {
        // Idem pour planète: on lit via DAO et mappe vers le domaine
        val entity = planetDao.getById(id)
            ?: error("Planet not found locally (id=$id)")
        return entity.toDomain()
    }
}
