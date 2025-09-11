package com.example.projet1diiage.manageSpace.domain.repository
import com.example.projet1diiage.manageSpace.domain.entity.Space

interface ManageSpaceRepository {
    suspend fun createSpace(space: Space): Space
    // plus tard: suspend fun updateSpace(space: Space): Space
    // plus tard: suspend fun disableSpace(id: String): Space
}