package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("large")
    val large: Large?,
    @SerializedName("small")
    val small: Small?,
    @SerializedName("tiny")
    val tiny: Tiny?
)