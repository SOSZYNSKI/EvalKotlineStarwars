package com.example.projet1diiage.StarWars.domain.usecase

import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository

// Use case = une action métier isolée. Ici: récupérer une personne par id.
class GetPersonUseCase(private val repo: StarWarsRepository) {
    suspend operator fun invoke(id: String): Person = repo.getPerson(id)
}
