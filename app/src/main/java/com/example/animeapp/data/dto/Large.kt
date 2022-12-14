package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class Large(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("width")
    val width: Int?
)