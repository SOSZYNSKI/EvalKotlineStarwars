package com.example.projet1diiage.StarWars.data.remote

import com.squareup.moshi.Json

data class PlanetPropertiesDto(
    @Json(name = "name") val name: String,
    @Json(name = "climate") val climate: String? = null,
    @Json(name = "diameter") val diameter: String? = null,
    @Json(name = "gravity") val gravity: String? = null,
    @Json(name = "population") val population: String? = null
)
