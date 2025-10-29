package com.example.projet1diiage.StarWars.domain.usecase

import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository

// Use case: rechercher des personnes avec un texte et une page
class SearchPeopleUseCase(private val repo: StarWarsRepository) {
    suspend operator fun invoke(query: String, page: Int = 1): List<Person> =
        repo.searchPeople(query, page)
}
