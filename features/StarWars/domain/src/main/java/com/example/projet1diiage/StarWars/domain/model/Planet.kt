package com.example.projet1diiage.StarWars.domain.model


// Modèle domaine pour une planète
data class Planet(
    val id: String,
    val name: String,
    val climate: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val population: String? = null
)
