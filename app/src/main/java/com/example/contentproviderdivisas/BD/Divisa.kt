package com.example.contentproviderdivisas.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisas")
data class Divisa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "base_code") val baseCode: String,
    @ColumnInfo(name = "nombre_divisa") val nombreDivisa: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "fecha") val fecha: String
)
