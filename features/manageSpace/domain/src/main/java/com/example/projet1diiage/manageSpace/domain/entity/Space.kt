package com.example.projet1diiage.manageSpace.domain.entity


data class Space(
    val id: String = "",            // sera généré côté Data
    val name: String,               // ex: "Salle Atlas"
    val description: String,        // ex: "Grande salle de réunion"
    val photo64: String?,          // null si pas de photo
    val type: SpaceType,
    val capacity: Int,              // capacité max > 0
    val options: List<SpaceOption> = emptyList(),
    val active: Boolean = true      // un espace est actif à la création
)