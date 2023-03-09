package com.example.contentproviderdivisas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var workManager: WorkManager? = null
    private var workRequest: PeriodicWorkRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creamos el PeriodicWorkRequest
        workRequest = PeriodicWorkRequest.Builder(CurrencyWorker::class.java, 15, TimeUnit.MINUTES)
            .addTag("currency_worker").build()

        // Cancelamos el trabajo anterior para evitar que se ejecuten en paralelo
        workManager = WorkManager.getInstance(applicationContext)
        workManager?.cancelAllWorkByTag("currency_worker")

        // Agregamos el trabajo al WorkManager
        workManager?.enqueue(workRequest!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancelamos el trabajo al destruir la actividad
        workManager?.cancelAllWorkByTag("currency_worker")
    }

}

