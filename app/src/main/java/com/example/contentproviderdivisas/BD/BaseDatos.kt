package com.example.contentproviderdivisas.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Divisas::class], version = 1)
abstract class BaseDatos : RoomDatabase() {
    abstract fun divisaDao(): DAODivisas

    companion object {
        @Volatile
        private var INSTANCE: BaseDatos? = null

        fun getDatabase(context: Context): BaseDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, BaseDatos::class.java, "divisa_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
