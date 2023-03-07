package com.example.contentproviderdivisas.BD

import androidx.room.*

@Dao
interface DAODivisas {
    @Insert
    suspend fun insert(divisa: Divisas)

    @Query("SELECT * FROM divisas")
    suspend fun getAll(): List<Divisas>
}
