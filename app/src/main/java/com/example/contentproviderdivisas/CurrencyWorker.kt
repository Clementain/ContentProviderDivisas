package com.example.contentproviderdivisas

import android.content.Context
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.contentproviderdivisas.BD.Divisa
import com.example.contentproviderdivisas.BD.DivisaDatabase
import com.example.contentproviderdivisas.Overview.OverviewViewModel
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class CurrencyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    private lateinit var overviewViewModel: OverviewViewModel

    val db = Room.databaseBuilder(
        applicationContext, DivisaDatabase::class.java, "DivisasDatabase.db"
    ).build()
    val divisaDao = db.divisaDao()
    val tasasCambio = overviewViewModel.mon
    override fun doWork(): Result {
        runBlocking {
            val f = LocalDate.now().toString()
            for ((key, value) in tasasCambio.rates.entries) {
                val divisa = Divisa(
                    baseCode = tasasCambio.baseCode, nombreDivisa = key, valor = value, fecha = f
                )
                divisaDao.insertDivisa(divisa)
            }
        }
        return Result.success()
    }
}