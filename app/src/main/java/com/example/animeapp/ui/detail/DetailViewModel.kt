package com.example.animeapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.R
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.module.AnimeEntity
import com.example.animeapp.data.mappers.AnimeSingleMapper
import com.example.animeapp.domain.GetAnimeWithIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAnimeWithIdUseCase: GetAnimeWithIdUseCase,
    private val animeSingleMapper: AnimeSingleMapper<AnimeEntity, DetailUiData>
) : ViewModel() {
    private val _animeDetailUiState= MutableLiveData<DetailUiState>()
    val animeDetailUiState:LiveData<DetailUiState> get() = _animeDetailUiState

    fun getAnimeWithId(id:String){
        viewModelScope.launch {
            getAnimeWithIdUseCase(id).collectLatest {
                when(it){
                    is NetworkResponseState.Success->{
                        println(it.result)
                        _animeDetailUiState.postValue(DetailUiState.Success(animeSingleMapper.map(it.result)))
                    }
                    is NetworkResponseState.Loading->{
                        _animeDetailUiState.postValue(DetailUiState.Loading)
                    }
                    is NetworkResponseState.Error->{
                        _animeDetailUiState.postValue(DetailUiState.Error(R.string.error))
                    }

                }
            }
        }
    }

}