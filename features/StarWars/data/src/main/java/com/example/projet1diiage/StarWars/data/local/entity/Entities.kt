package com.example.projet1diiage.StarWars.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entité de mes classes pour ma data Room
@Entity(tableName = "people")
data class PersonEntity(
    @PrimaryKey val id: String,
    val name: String,
    val height: String? = null,
    val mass: String? = null,
    val birthYear: String? = null,
    val gender: String? = null,
    val homeworldUrl: String? = null
)

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val climate: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val population: String? = null
)
