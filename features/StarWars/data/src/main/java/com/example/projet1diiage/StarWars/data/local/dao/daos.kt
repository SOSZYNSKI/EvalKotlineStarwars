package com.example.projet1diiage.StarWars.data.local.dao

import androidx.room.*
import com.example.projet1diiage.StarWars.data.local.entity.PersonEntity
import com.example.projet1diiage.StarWars.data.local.entity.PlanetEntity
import kotlinx.coroutines.flow.Flow

// DAO: C'est le fichier "Repsository" sc'est l'interface à implemetens dans StarWarsRepositoryRoomImpl
//Comme pour repos interface dans domain. C'est pour être sur d'avoir ce plan dan,s le repo et s'assuré des datas


@Dao
interface PersonDao {
    @Query("SELECT * FROM people ORDER BY name")
    suspend fun getAll(): List<PersonEntity>
    @Query("SELECT * FROM people ORDER BY name")

    // observeAll = met à jour la liste en temps réel
    fun observeAll(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM people WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): PersonEntity?

    @Query("SELECT * FROM people WHERE name LIKE '%' || :query || '%' ORDER BY name")
    suspend fun searchByName(query: String): List<PersonEntity>

    // upsert = insert ou update (meme cle)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PersonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: PersonEntity)
}

@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets ORDER BY name")
    suspend fun getAll(): List<PlanetEntity>
    @Query("SELECT * FROM planets ORDER BY name")
    fun observeAll(): Flow<List<PlanetEntity>>

    @Query("SELECT * FROM planets WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): PlanetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PlanetEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: PlanetEntity)
}

