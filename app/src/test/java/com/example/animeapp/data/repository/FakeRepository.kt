package com.example.animeapp.data.repository

import com.example.animeapp.animeEntity
import com.example.animeapp.animeList
import com.example.animeapp.apiException
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.mappers.module.AnimeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : AnimeRepository {

    private var showError=false

    fun updateShowError(showError:Boolean){
        this.showError = showError
    }


    override suspend fun getAnimeWithCategories(categoryQuery: String): Flow<NetworkResponseState<List<AnimeEntity>>> {
        return flow {
            if (showError){
            NetworkResponseState.Error(apiException)
        }else{
            NetworkResponseState.Success(animeList)
         }
        }

    }

    override suspend fun getAnimeWithId(animeId: String): Flow<NetworkResponseState<AnimeEntity>> {
        return flow {
            if (showError){
                NetworkResponseState.Error(apiException)
            }else{
                NetworkResponseState.Success(animeEntity)
            }
        }
    }

}