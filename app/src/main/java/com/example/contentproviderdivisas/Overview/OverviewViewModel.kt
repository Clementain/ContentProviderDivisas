package com.example.contentproviderdivisas.Overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contentproviderdivisas.Internet.ExchangeApi
import com.example.contentproviderdivisas.Internet.Moneda
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    lateinit var mon: Moneda

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
                mon = listResult

            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
