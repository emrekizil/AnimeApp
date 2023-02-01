package com.example.animeapp.domain

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeWithIdUseCaseImpl @Inject constructor(
    private val animeRepository: AnimeRepository
) : GetAnimeWithIdUseCase {
    override suspend fun invoke(animeId: String) = animeRepository.getAnimeWithId(animeId)





        /*
        flow{
            emit(NetworkResponseState.Loading)
            when(val response = animeRepository.getAnimeWithId(animeId)){
                is NetworkResponseState.Error -> emit(response)
                NetworkResponseState.Loading ->Unit
                is NetworkResponseState.Success -> emit(
                    NetworkResponseState.Success(
                        animeSingleMapper.map(
                            response.result
                        )
                    )
                )
            }
        }*/
}