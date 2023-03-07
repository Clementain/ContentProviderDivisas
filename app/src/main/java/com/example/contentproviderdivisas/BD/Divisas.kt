package com.example.contentproviderdivisas.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisas")
data class Divisas(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo(name = "base_code") val baseCode: String,

    @ColumnInfo(name = "nombre") val nombre: String,

    @ColumnInfo(name = "valor") val valor: Double
)
