package com.example.projet1diiage.manageSpace.domain.usecase

import com.example.projet1diiage.manageSpace.domain.entity.Space
import com.example.projet1diiage.manageSpace.domain.repository.ManageSpaceRepository

class CreateSpaceUseCase(
    private val repo: ManageSpaceRepository
) {
    suspend operator fun invoke(space: Space): Space {
        // ✅ règles métier ici (pas dans l'UI / pas dans Data)
        require(space.name.isNotBlank()) { "Le nom est obligatoire" }
        require(space.capacity > 0) { "La capacité doit être > 0" }
        // (optionnel) normalisations
        val normalized = space.copy(
            name = space.name.trim(),
            description = space.description.trim()
        )
        return repo.createSpace(normalized)
    }
}