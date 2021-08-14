package com.rijaldev.herbalplants.room

import androidx.room.*

@Dao
interface HerbalDao {

    @Insert
    suspend fun addHerbal(herbalR: HerbalR)

    @Query("SELECT * FROM herbalr")
    suspend fun getHerbals(): List<HerbalR>

    @Update
    suspend fun updateHerbal(herbalR: HerbalR)

    @Delete
    suspend fun deleteHerbal(herbalR: HerbalR)
}