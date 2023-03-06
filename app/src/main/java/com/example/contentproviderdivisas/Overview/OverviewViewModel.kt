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

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMonedasValor("USD")
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    fun getMonedasValor(moneda: String) {
        viewModelScope.launch {
            try {
                val listResult = ExchangeApi.retrofitService.getMonedas(moneda)
                _status.value = listResult.toString()
                for ((key, value) in listResult.rates.entries) {
                    val div = Divisas()
                    div.baseCode = listResult.baseCode
                    div.nomD = key
                    div.valD = value
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
