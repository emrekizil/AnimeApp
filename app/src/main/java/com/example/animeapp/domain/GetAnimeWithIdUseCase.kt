package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.mappers.module.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface GetAnimeWithIdUseCase {
    suspend operator fun invoke(animeId:String):Flow<NetworkResponseState<AnimeEntity>>
}