package com.example.animeapp.data.source

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.api.AnimeApi
import com.example.animeapp.data.dto.Data
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val animeApi: AnimeApi):RemoteDataSource {
    override suspend fun getAnimeWithCategories(categoryQuery:String): NetworkResponseState<List<Data>> =
        try {
            val response = animeApi.getAnimeWithCategories(categoryQuery)
            NetworkResponseState.Success(response.data)
        }catch (e:Exception){
            NetworkResponseState.Error(e)
        }
}