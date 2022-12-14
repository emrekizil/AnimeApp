package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class StreamingLinks(
    @SerializedName("links")
    val links: LinksX?
)