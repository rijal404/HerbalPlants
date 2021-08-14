package com.rijaldev.herbalplants.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HerbalR (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val names: String,
    val details: String,
    val photos: Int = 0
)