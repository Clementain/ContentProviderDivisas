package com.example.contentproviderdivisas

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.contentproviderdivisas.BD.Divisa
import com.example.contentproviderdivisas.BD.DivisaDatabase
import com.example.contentproviderdivisas.Overview.OverviewViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext, DivisaDatabase::class.java, "DivisasDatabase.db"
        ).build()
        val divisaDao = db.divisaDao()
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]


        val buscarButton = findViewById<Button>(R.id.btnBuscar)
        buscarButton.setOnClickListener {
            val tasasCambio = overviewViewModel.mon
            val f = LocalDate.now().toString()
            for ((key, value) in tasasCambio.rates.entries) {
                val divisa = Divisa(
                    baseCode = tasasCambio.baseCode, nombreDivisa = key, valor = value, fecha = f
                )
                lifecycleScope.launch {
                    divisaDao.insertDivisa(divisa)
                }

            }
        }


    }
}
