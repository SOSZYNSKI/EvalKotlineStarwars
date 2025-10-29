package com.example.projet1diiage.StarWars.data.mapper

import com.example.projet1diiage.StarWars.data.remote.PersonPropertiesDto
import com.example.projet1diiage.StarWars.data.remote.PlanetPropertiesDto
import com.example.projet1diiage.StarWars.data.remote.SwapiBriefDto
import com.example.projet1diiage.StarWars.data.remote.SwapiItemDto
import com.example.projet1diiage.StarWars.domain.model.Person
import com.example.projet1diiage.StarWars.domain.model.Planet

// Ici je transforme les réponses de l'API Star Wars en objets simples que l'appli utilise (Person, Planet).
// En gros : on garde l'API d'un côté, et ce que l'app affiche de l'autre.

private fun extractId(url: String): String = url.trimEnd('/').substringAfterLast('/')
//je récupère l'id à la fin de l'URL fournie par l'API

fun SwapiItemDto<PersonPropertiesDto>.toDomain(): Person = Person(
    id = result.uid,
    name = result.properties.name,
    height = result.properties.height,
    mass = result.properties.mass,
    birthYear = result.properties.birthYear,
    gender = result.properties.gender,
    homeworldUrl = result.properties.homeworld
)

fun SwapiItemDto<PlanetPropertiesDto>.toDomain(): Planet = Planet(
    id = result.uid,
    name = result.properties.name,
    climate = result.properties.climate,
    diameter = result.properties.diameter,
    gravity = result.properties.gravity,
    population = result.properties.population
)

fun SwapiBriefDto.toPersonDomain(): Person = Person(
    id = extractId(url),
    name = name
)

