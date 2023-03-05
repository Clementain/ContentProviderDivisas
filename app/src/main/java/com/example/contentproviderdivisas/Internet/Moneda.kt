package com.example.contentproviderdivisas.Internet

import com.squareup.moshi.Json

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Moneda(
    val base_code: String,
    @Json(name = "rates") val divisas: ArrayList<Pair<String, Double>>
)