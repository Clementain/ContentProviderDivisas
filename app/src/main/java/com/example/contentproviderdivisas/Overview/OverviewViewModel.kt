package com.example.contentproviderdivisas.Overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contentproviderdivisas.BD.Divisas
import com.example.contentproviderdivisas.Internet.ExchangeApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status


    init {
        getMonedasValor("USD")
    }

    fun getMonedasValor(moneda: String) {
        viewModelScope.launch {
            try {
                val listResult = ExchangeApi.retrofitService.getMonedas(moneda)
                _status.value = listResult.toString()
                for ((key, value) in listResult.rates.entries) {
                    val divisa =
                        Divisas(baseCode = listResult.baseCode, nombre = key, valor = value)
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
