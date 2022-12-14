package com.example.animeapp.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.R
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.AnimeEntity
import com.example.animeapp.domain.AnimeListMapper
import com.example.animeapp.domain.GetAnimeWithCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAnimeWithCategoriesUseCase: GetAnimeWithCategoriesUseCase,
    private val animeListMapper: AnimeListMapper< AnimeEntity, HomeUiData>
) : ViewModel() {
    private val _animeHomeUiState=MutableLiveData<HomeUiState>()
    val animeHomeUiState:LiveData<HomeUiState> get() = _animeHomeUiState

    fun getAnimeWithCategories(categoryQuery:String){
        viewModelScope.launch {
            getAnimeWithCategoriesUseCase(categoryQuery).onEach {
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

data class HomeUiData(val name:String?,val imageUrl:String?)

sealed class HomeUiState{
    object Loading:HomeUiState()
    data class Success(val data:List<HomeUiData>):HomeUiState()
    data class Error(@StringRes val message:Int):HomeUiState()
}