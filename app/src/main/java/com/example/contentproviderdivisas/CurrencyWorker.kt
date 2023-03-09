package com.example.contentproviderdivisas

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.contentproviderdivisas.BD.Divisa
import com.example.contentproviderdivisas.BD.DivisaDatabase
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class CurrencyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    val db = Room.databaseBuilder(
        applicationContext, DivisaDatabase::class.java, "DivisasDatabase.db"
    ).build()
    val divisaDao = db.divisaDao()
    override fun doWork(): Result {
        val data = inputData
        val tasasCambio = data.getString("baseCode")
        val r = data.getString("rates")
        val keyValuePairs = r?.split(",") // Separa la cadena en pares clave-valor
        val dictionary = mutableMapOf<String, Double>() // Crea un diccionario vacío
        for (keyValuePair in keyValuePairs!!) {
            val pair = keyValuePair.split("=")
            val key = pair[0]
            val value = pair[1].toDouble()
            dictionary[key] = value
        }
        runBlocking {
            val f = LocalDate.now().toString()
            for ((key, value) in dictionary) {
                val divisa = tasasCambio?.let {
                    Divisa(
                        baseCode = it, nombreDivisa = key, valor = value, fecha = f
                    )
                }
                divisa?.let { divisaDao.insertDivisa(it) }
            }
        }
        Log.d("resultado", "sisepudo")
        return Result.success()
    }
}