package com.example.contentproviderdivisas

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.contentproviderdivisas.Overview.OverviewViewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var overviewViewModel: OverviewViewModel

    private var workManager: WorkManager? = null
    private var workRequest: PeriodicWorkRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        todo()
    }

    private fun todo() {
        val tasasCambio = overviewViewModel.mon
        val inputData = Data.Builder().putString("baseCode", tasasCambio.baseCode)
            .putString("rates", tasasCambio.rates.toString()).build()
        // Creamos el PeriodicWorkRequest
        workRequest = PeriodicWorkRequest.Builder(CurrencyWorker::class.java, 15, TimeUnit.MINUTES)
            .addTag("currency_worker").setInputData(inputData).build()

        // Cancelamos el trabajo anterior para evitar que se ejecuten en paralelo
        workManager = WorkManager.getInstance(applicationContext)
        workManager?.cancelAllWorkByTag("currency_worker")

        // Agregamos el trabajo al WorkManager
        workManager?.enqueue(workRequest!!)

        Log.d("Resultado", "Se supone si todo sale bien aqui llega esto")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancelamos el trabajo al destruir la actividad
        workManager?.cancelAllWorkByTag("currency_worker")
    }

}