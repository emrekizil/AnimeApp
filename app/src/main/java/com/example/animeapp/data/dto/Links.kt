package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    val self: String?
)