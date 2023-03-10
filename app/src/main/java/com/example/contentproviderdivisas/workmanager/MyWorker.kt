package com.example.contentproviderdivisas.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.contentproviderdivisas.BD.Divisa
import com.example.contentproviderdivisas.BD.DivisaDatabase
import com.example.contentproviderdivisas.Internet.ExchangeRateApiService
import com.example.contentproviderdivisas.Model.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ExchangeRateApiService = retrofit.create(ExchangeRateApiService::class.java)

        val call: Call<Posts> = api.posts

        call.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (!response.isSuccessful) {
                    return
                }

                val post = response.body()
                val moneda = Divisa(
                    id = 0,
                    baseCode = "",
                    nombreDivisa = "",
                    valor = 0.0,
                    fecha = ""
                )
                for (codes in post!!.conversion_ratesonversions) {
                    moneda.baseCode = codes.key
                    moneda.valor = codes.value
                    DivisaDatabase.getDatabase(applicationContext).divisaDao()
                        .insertDivisa(moneda)
                }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
            }
        })

        return Result.success()
    }
}