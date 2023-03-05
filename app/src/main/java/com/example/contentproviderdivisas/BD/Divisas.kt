package com.example.contentproviderdivisas.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "divisas")
class Divisas : Serializable {
    @PrimaryKey()
    var baseCode: String? = null

    @ColumnInfo(name = "nombreDivisa")
    var nomD: String? = null

    @ColumnInfo(name = "valorDivisa")
    var valD: Double? = null

}