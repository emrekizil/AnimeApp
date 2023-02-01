package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface GetAnimeWithIdUseCase {
    operator suspend fun invoke(animeId:String):Flow<NetworkResponseState<AnimeEntity>>
}