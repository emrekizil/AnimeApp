package com.example.animeapp.data.repository

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.module.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
     suspend fun getAnimeWithCategories(categoryQuery:String): Flow<NetworkResponseState<List<AnimeEntity>>>

     suspend fun getAnimeWithId(animeId:String):Flow<NetworkResponseState<AnimeEntity>>
}