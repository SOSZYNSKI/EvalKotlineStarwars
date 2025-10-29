package com.example.projet1diiage.StarWars.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projet1diiage.StarWars.data.local.dao.PersonDao
import com.example.projet1diiage.StarWars.data.local.dao.PlanetDao
import com.example.projet1diiage.StarWars.data.local.entity.PersonEntity
import com.example.projet1diiage.StarWars.data.local.entity.PlanetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Ici je crée la base Room pour stocker en local (personnes + planètes)
@Database(
    entities = [PersonEntity::class, PlanetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun planetDao(): PlanetDao

    companion object {
        // Construit la DB. prepopulate=true = insère quelques données par défaut
        fun build(context: Context, prepopulate: Boolean = true): StarWarsDatabase {
            return Room.databaseBuilder(context, StarWarsDatabase::class.java, "starwars.db")
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        if (!prepopulate) return

                        // J'insère des exemples au premier lancement pour tester rapidement
                        CoroutineScope(Dispatchers.IO).launch {
                            val appDb = Room.databaseBuilder(
                                context,
                                StarWarsDatabase::class.java,
                                "starwars.db"
                            ).build()

                            // Quelques planètes (ids SWAPI usuels)
                            val planets = listOf(
                                PlanetEntity(
                                    id = "1",
                                    name = "Tatooine",
                                    climate = "arid",
                                    diameter = "10465",
                                    gravity = "1 standard",
                                    population = "200000"
                                ),
                                PlanetEntity(
                                    id = "2",
                                    name = "Alderaan",
                                    climate = "temperate",
                                    diameter = "12500",
                                    gravity = "1 standard",
                                    population = "2000000000"
                                )
                            )
                            appDb.planetDao().upsertAll(planets)

                            // Quelques personnes
                            val people = listOf(
                                PersonEntity(
                                    id = "1",
                                    name = "Luke Skywalker",
                                    height = "172",
                                    mass = "77",
                                    birthYear = "19BBY",
                                    gender = "male",
                                    homeworldUrl = "https://www.swapi.tech/api/planets/1"
                                ),
                                PersonEntity(
                                    id = "5",
                                    name = "Leia Organa",
                                    height = "150",
                                    mass = "49",
                                    birthYear = "19BBY",
                                    gender = "female",
                                    homeworldUrl = "https://www.swapi.tech/api/planets/2"
                                )
                            )
                            appDb.personDao().upsertAll(people)

                            appDb.close()
                        }
                    }
                })
                .build()
        }
    }
}

