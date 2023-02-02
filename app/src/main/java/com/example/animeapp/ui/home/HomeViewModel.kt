package com.example.animeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.R
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.mappers.module.AnimeEntity
import com.example.animeapp.data.mappers.AnimeListMapper
import com.example.animeapp.domain.GetAnimeWithCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAnimeWithCategoriesUseCase: GetAnimeWithCategoriesUseCase,
    private val animeListMapper: AnimeListMapper<AnimeEntity, HomeUiData>
) : ViewModel() {
    private val _animeHomeUiState=MutableLiveData<HomeUiState>()
    val animeHomeUiState:LiveData<HomeUiState> get() = _animeHomeUiState

    fun getAnimeWithCategories(categoryQuery:String){
        viewModelScope.launch {
            //We should get latest response state because it reflects ui.It explains why we use collect latest
            getAnimeWithCategoriesUseCase(categoryQuery).collectLatest{
                when(it){
                    is NetworkResponseState.Success->{
                        _animeHomeUiState.postValue(HomeUiState.Success(animeListMapper.map(it.result)))
                    }
                    is NetworkResponseState.Error->{
                        _animeHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    is NetworkResponseState.Loading->{
                        _animeHomeUiState.postValue(HomeUiState.Loading)
                    }
                }
            }
        }
    }
}

