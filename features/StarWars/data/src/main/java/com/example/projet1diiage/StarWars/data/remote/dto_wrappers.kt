package com.example.projet1diiage.StarWars.data.remote

// je rassemble ici les "wrappers" renvoyés par SWAPI.
// Pourquoi ? L'API ne renvoie pas directement l'objet, mais un "result.properties" ou une liste "results".
// Du coup on a des DTO génériques (SwapiItemDto, SwapiResultDto, SwapiSearchDto...) utilisés par le service puis mappés vers le domaine.
import com.squareup.moshi.Json

data class SwapiItemDto<T>(
    @Json(name = "message") val message: String? = null,
    @Json(name = "result") val result: SwapiResultDto<T>
)

data class SwapiResultDto<T>(
    @Json(name = "properties") val properties: T,
    @Json(name = "uid") val uid: String,
    @Json(name = "url") val url: String,
    @Json(name = "description") val description: String? = null
)

data class SwapiSearchDto<B>(
    @Json(name = "message") val message: String? = null,
    @Json(name = "total_records") val totalRecords: Int? = null,
    @Json(name = "results") val results: List<B> = emptyList()
)

data class SwapiBriefDto(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String,
    @Json(name = "uid") val uid: String? = null
)

