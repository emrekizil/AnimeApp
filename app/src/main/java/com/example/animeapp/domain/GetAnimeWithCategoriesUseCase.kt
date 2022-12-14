package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface GetAnimeWithCategoriesUseCase {
    operator fun invoke(categoryQuery: String): Flow<NetworkResponseState<List<AnimeEntity>>>
}