package com.example.contentproviderdivisas.BD

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Divisa::class], version = 1)
abstract class DivisaDatabase : RoomDatabase() {
    abstract fun divisaDao(): DivisaDao
}
