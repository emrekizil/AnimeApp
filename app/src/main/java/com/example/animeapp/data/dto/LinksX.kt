package com.example.animeapp.data.dto


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("related")
    val related: String?,
    @SerializedName("self")
    val self: String?
)