package com.example.contentproviderdivisas.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Divisas::class], version = 1, exportSchema = false
)
abstract class BaseDatos : RoomDatabase() {

    companion object {
        var baseDatos: BaseDatos? = null

        @Synchronized
        fun getBaseDatos(context: Context): BaseDatos {
            if (baseDatos == null) {
                baseDatos =
                    Room.databaseBuilder(context, BaseDatos::class.java, "monedas.db").build()
            }
            return baseDatos!!
        }

    }

    abstract fun dAODivisas(): DAODivisas

}