package com.example.projet1diiage.StarWars.data.mapper

import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.model.Planet
import com.example.projet1diiage.StarWars.data.local.entity.PersonEntity
import com.example.projet1diiage.StarWars.data.local.entity.PlanetEntity

// Ici je convertis entre ce que Room stocke (Entity) et ce que l'appli manipule (Person, Planet).
// Pourquoi: séparer la base et l'UI pour que chacun reste simple.

fun PersonEntity.toDomain() = Person(
    id = id,
    name = name,
    height = height,
    mass = mass,
    birthYear = birthYear,
    gender = gender,
    homeworldUrl = homeworldUrl
)

fun Person.toEntity() = PersonEntity(
    id = id,
    name = name,
    height = height,
    mass = mass,
    birthYear = birthYear,
    gender = gender,
    homeworldUrl = homeworldUrl
)

fun PlanetEntity.toDomain() = Planet(
    id = id,
    name = name,
    climate = climate,
    diameter = diameter,
    gravity = gravity,
    population = population
)

// de l'appli -> objet pour la base (Planet)
fun Planet.toEntity() = PlanetEntity(
    id = id,
    name = name,
    climate = climate,
    diameter = diameter,
    gravity = gravity,
    population = population
)
