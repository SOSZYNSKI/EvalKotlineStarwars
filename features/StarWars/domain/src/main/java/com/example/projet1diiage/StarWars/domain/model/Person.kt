package com.example.projet1diiage.StarWars.domain.model

// Modèle domaine pour une personne (utilisé par l'UI)
data class Person(
    val id: String,
    val name: String,
    val height: String? = null,
    val mass: String? = null,
    val birthYear: String? = null,
    val gender: String? = null,
    val homeworldUrl: String? = null
)
