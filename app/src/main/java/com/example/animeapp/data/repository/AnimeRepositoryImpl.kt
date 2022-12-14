package com.example.animeapp.data.repository

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.source.RemoteDataSource
import com.example.animeapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AnimeRepository {
    override suspend fun getAnimeWithCategories(categoryQuery:String): NetworkResponseState<List<Data>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getAnimeWithCategories(categoryQuery)
            } catch (e: Exception) {
                NetworkResponseState.Error(e)
            }
        }
}