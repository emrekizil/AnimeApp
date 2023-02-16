package com.example.animeapp.domain


import com.example.animeapp.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimeWithCategoriesUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository
): GetAnimeWithCategoriesUseCase {
    override suspend fun invoke(categoryQuery: String) = animeRepository.getAnimeWithCategories(categoryQuery)

}