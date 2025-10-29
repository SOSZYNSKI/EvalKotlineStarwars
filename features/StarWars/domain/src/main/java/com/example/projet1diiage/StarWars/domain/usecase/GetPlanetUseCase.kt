package com.example.projet1diiage.StarWars.domain.usecase

import com.example.projet1diiage.StarWars.domain.model.Planet
import com.example.projet1diiage.StarWars.domain.repository.StarWarsRepository

// Use case: récupérer une planète par id
class GetPlanetUseCase(private val repo: StarWarsRepository) {
    suspend operator fun invoke(id: String): Planet = repo.getPlanet(id)
}
