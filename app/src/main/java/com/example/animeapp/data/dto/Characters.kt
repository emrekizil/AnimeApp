package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("links")
    val links: LinksX?
)