package com.example.contentproviderdivisas.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Divisa::class], version = 1)
abstract class DivisaDatabase : RoomDatabase() {
    abstract fun divisaDao(): DivisaDao

    companion object {
        private var INSTANCE: DivisaDatabase? = null
        fun getDatabase(context: Context): DivisaDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, DivisaDatabase::class.java, "moneda")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
