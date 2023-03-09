package com.example.contentproviderdivisas.Overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contentproviderdivisas.Internet.ExchangeApi
import com.example.contentproviderdivisas.Internet.Moneda
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    var mon: Moneda = Moneda(
        "",
        "",
        "",
        "",
        0,
        "",
        0,
        "",
        0,
        "",
        mutableMapOf<String, Double>().withDefault { 0.0 }) // Asignación de valor predeterminado

    init {
        getMonedasValor()
    }

    fun getMonedasValor() {
        viewModelScope.launch {
            try {
                mon = ExchangeApi.retrofitService.getMonedas()

            } catch (_: Exception) {

            }
        }
    }
}
