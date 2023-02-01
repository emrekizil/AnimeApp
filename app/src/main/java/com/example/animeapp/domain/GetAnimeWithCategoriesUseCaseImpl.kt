package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeWithCategoriesUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetAnimeWithCategoriesUseCase {
    override suspend fun invoke(categoryQuery: String) = animeRepository.getAnimeWithCategories(categoryQuery)

}