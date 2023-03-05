package com.example.contentproviderdivisas.BD

import androidx.room.*

@Dao
interface DAODivisas {
    @Query("SELECT * FROM divisas WHERE baseCode= :baseCode")
    suspend fun obtenerDivisas(baseCode: String): List<Divisas>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDivisas(divisas: Divisas)

    @Delete
    suspend fun borrarDivisas(divisas: Divisas)

    @Update
    suspend fun actualizarDivisa(divisas: Divisas)

    @Query("SELECT * FROM divisas WHERE baseCode=:baseCode and nombreDivisa=:nomD")
    suspend fun obtenerDivisa(baseCode: String, nomD: String): Divisas


}