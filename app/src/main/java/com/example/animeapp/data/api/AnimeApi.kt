package com.example.animeapp.data.api

import com.example.animeapp.data.dto.AnimeResponse
import com.example.animeapp.data.dto.AnimeSingleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {
    @GET("anime")
    suspend fun getAnimeWithCategories(@Query("filter[categories]") categoryQuery: String):AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeWithId(@Path("id") animeId:Int):AnimeSingleResponse
}