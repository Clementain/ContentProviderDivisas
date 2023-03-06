package com.example.contentproviderdivisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.contentproviderdivisas.Overview.OverviewViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener una instancia del ViewModel
        overviewViewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        // Observar la variable "status" en el ViewModel y actualizar el TextView en consecuencia
        overviewViewModel.status.observe(this, { status ->
            findViewById<TextView>(R.id.status_text_view).text = status
        })
    }
}
