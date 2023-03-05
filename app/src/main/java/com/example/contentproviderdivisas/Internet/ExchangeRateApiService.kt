package com.example.contentproviderdivisas.Internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://open.er-api.com"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface ExchangeRateApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("/v6/latest/{valor}")
    suspend fun getMonedas(@Path("valor") valor: String): List<Moneda>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ExchangeApi {
    val retrofitService: ExchangeRateApiService by lazy { retrofit.create(ExchangeRateApiService::class.java) }
}