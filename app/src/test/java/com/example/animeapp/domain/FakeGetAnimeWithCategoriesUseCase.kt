package com.example.animeapp.domain

import com.example.animeapp.animeList
import com.example.animeapp.apiException
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.module.AnimeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAnimeWithCategoriesUseCase : GetAnimeWithCategoriesUseCase{
    private var showError = false

    fun updateShowError(showError:Boolean){
        this.showError=showError
    }


    override suspend fun invoke(categoryQuery: String): Flow<NetworkResponseState<List<AnimeEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            if(showError){
                emit(NetworkResponseState.Error(apiException))
            }else{
                emit(NetworkResponseState.Success(animeList))
            }
        }


}