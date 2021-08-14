package com.rijaldev.herbalplants.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HerbalR::class], version = 1)
abstract class HerbalDB: RoomDatabase() {

    abstract fun herbalDao() : HerbalDao

    companion object {

        @Volatile private var instance: HerbalDB? = null
        private val LOCK =  Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HerbalDB::class.java,
            "herbal1234.db"
        ).build()
    }
}