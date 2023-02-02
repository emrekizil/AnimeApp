package com.example.animeapp.domain


import com.example.animeapp.data.repository.AnimeRepository
import javax.inject.Inject

class GetAnimeWithIdUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository
) : GetAnimeWithIdUseCase {
    override suspend fun invoke(animeId: String) = animeRepository.getAnimeWithId(animeId)

}