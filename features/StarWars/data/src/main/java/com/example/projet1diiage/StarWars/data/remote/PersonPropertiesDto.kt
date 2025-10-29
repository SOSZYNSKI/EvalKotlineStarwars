package com.example.projet1diiage.StarWars.data.remote

import com.squareup.moshi.Json

data class PersonPropertiesDto(
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: String? = null,
    @Json(name = "mass") val mass: String? = null,
    @Json(name = "birth_year") val birthYear: String? = null,
    @Json(name = "gender") val gender: String? = null,
    @Json(name = "homeworld") val homeworld: String? = null
)
