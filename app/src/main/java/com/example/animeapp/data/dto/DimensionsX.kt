package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class DimensionsX(
    @SerializedName("large")
    val large: Large?,
    @SerializedName("medium")
    val medium: Medium?,
    @SerializedName("small")
    val small: Small?,
    @SerializedName("tiny")
    val tiny: Tiny?
)