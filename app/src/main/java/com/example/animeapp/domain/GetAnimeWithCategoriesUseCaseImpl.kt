package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeWithCategoriesUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val animeListMapper: AnimeListMapper<Data,AnimeEntity>
): GetAnimeWithCategoriesUseCase {
    override fun invoke(categoryQuery: String): Flow<NetworkResponseState<List<AnimeEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when(val response= animeRepository.getAnimeWithCategories(categoryQuery)){
                is NetworkResponseState.Error -> emit(response)
                NetworkResponseState.Loading ->Unit
                is NetworkResponseState.Success ->emit(
                    NetworkResponseState.Success(
                        animeListMapper.map(
                            response.result
                        )
                    )
                )
            }
        }

}