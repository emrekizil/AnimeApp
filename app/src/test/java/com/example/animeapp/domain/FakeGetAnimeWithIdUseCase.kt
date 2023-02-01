package com.example.animeapp.domain

import com.example.animeapp.animeEntity
import com.example.animeapp.apiException
import com.example.animeapp.data.NetworkResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAnimeWithIdUseCase : GetAnimeWithIdUseCase {
    private var showError = false

    fun updateShowError(showError:Boolean){
        this.showError=showError
    }
    override suspend fun invoke(animeId: String): Flow<NetworkResponseState<AnimeEntity>> =
        flow {
            emit(NetworkResponseState.Loading)
            if (showError){
                emit(NetworkResponseState.Error(apiException))
            }else{
                emit(NetworkResponseState.Success(animeEntity))
            }
        }

}