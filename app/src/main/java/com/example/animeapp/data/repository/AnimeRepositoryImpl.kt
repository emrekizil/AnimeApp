package com.example.animeapp.data.repository

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.source.RemoteDataSource
import com.example.animeapp.di.IoDispatcher
import com.example.animeapp.domain.module.AnimeEntity
import com.example.animeapp.data.mappers.AnimeListMapper
import com.example.animeapp.data.mappers.AnimeSingleMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val animeListMapper: AnimeListMapper<Data, AnimeEntity>,
    private val animeSingleMapper: AnimeSingleMapper<Data, AnimeEntity>
) : AnimeRepository {
    override suspend fun getAnimeWithCategories(categoryQuery:String): Flow<NetworkResponseState<List<AnimeEntity>>> =
         flow {
            emit(NetworkResponseState.Loading)
            when(val response= remoteDataSource.getAnimeWithCategories(categoryQuery)){
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


    override suspend fun getAnimeWithId(animeId: String): Flow<NetworkResponseState<AnimeEntity>> =
        flow{
            emit(NetworkResponseState.Loading)
            when(val response = remoteDataSource.getAnimeWithId(animeId)){
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
        }


}