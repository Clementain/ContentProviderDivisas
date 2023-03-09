package com.example.contentproviderdivisas.BD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DivisaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDivisa(divisa: Divisa)
}
