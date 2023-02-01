package com.example.animeapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.animeapp.*
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.FakeGetAnimeWithIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class DetailViewModelTest {
    @Mock
    private lateinit var getAnimeWithIdUseCase: FakeGetAnimeWithIdUseCase

    @Mock
    private lateinit var observer: Observer<DetailUiState>

    private val animeDetailUiMapperImpl = AnimeDetailUiMapperImpl()

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = DetailViewModel(getAnimeWithIdUseCase,animeDetailUiMapperImpl)
        viewModel.animeDetailUiState.observeForever(observer)
    }
    @Test
    fun `when use case returned downloading is live data state downloading`(){
        runBlocking {
            Mockito.`when`(getAnimeWithIdUseCase.invoke("$SAMPLE_ID"))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                })
            viewModel.getAnimeWithId("$SAMPLE_ID")
            verify(observer).onChanged(DetailUiState.Loading)

        }
    }

    @Test
    fun `when use case returned downloading is live data state success`(){
        runBlocking {
            Mockito.`when`(getAnimeWithIdUseCase.invoke("$SAMPLE_ID"))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Success(animeEntity))
                })
            viewModel.getAnimeWithId("$SAMPLE_ID")
            verify(observer).onChanged(DetailUiState.Loading)
            verify(observer).onChanged(DetailUiState.Success(animeDetailUiData))

        }
    }
    @Test
    fun `when use case returned downloading is live data state error`(){
        runBlocking {
            Mockito.`when`(getAnimeWithIdUseCase.invoke("$SAMPLE_ID"))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Error(apiException))
                })
            viewModel.getAnimeWithId("$SAMPLE_ID")
            verify(observer).onChanged(DetailUiState.Loading)
            verify(observer).onChanged(DetailUiState.Error(R.string.error))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun shutdown() {
        Dispatchers.resetMain()
        viewModel.animeDetailUiState.removeObserver(observer)
    }



}