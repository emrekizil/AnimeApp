package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.module.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface GetAnimeWithCategoriesUseCase {
   suspend operator fun invoke(categoryQuery: String): Flow<NetworkResponseState<List<AnimeEntity>>>
}