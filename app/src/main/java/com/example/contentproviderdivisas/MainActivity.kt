package com.example.contentproviderdivisas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.contentproviderdivisas.BD.BaseDatos
import com.example.contentproviderdivisas.BD.Divisas
import com.example.contentproviderdivisas.Internet.Moneda
import com.example.contentproviderdivisas.Overview.OverviewViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener una instancia del ViewModel
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]

        // Observar la variable "status" en el ViewModel y actualizar el TextView en consecuencia
        overviewViewModel.status.observe(this) { status ->
            findViewById<TextView>(R.id.status_text_view).text = status
        }

        val monedaEditText = findViewById<EditText>(R.id.txtMoneda)
        val buscarButton = findViewById<Button>(R.id.btnBuscar)
        buscarButton.setOnClickListener {
            val moneda = monedaEditText.text.toString()
            overviewViewModel.getMonedasValor(moneda)
            lifecycleScope.launch {
                insertar(overviewViewModel.mon)
            }
        }
    }

    suspend fun insertar(mon: Moneda) {
        for ((key, value) in mon.rates.entries) {
            val divisa = Divisas(baseCode = mon.baseCode, nombre = key, valor = value)
            BaseDatos.getDatabase(this).divisaDao().insert(divisa)
        }
    }
}
