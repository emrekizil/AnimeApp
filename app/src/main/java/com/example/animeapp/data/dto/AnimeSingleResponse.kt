package com.example.animeapp.data.dto

import com.google.gson.annotations.SerializedName

data class AnimeSingleResponse(
    @SerializedName("data")
    val data: Data?,
)
