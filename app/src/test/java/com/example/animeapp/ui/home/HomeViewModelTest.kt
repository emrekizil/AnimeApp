package com.example.animeapp.ui.home
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.animeapp.*
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.domain.FakeGetAnimeWithCategoriesUseCase
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

class HomeViewModelTest {
    @Mock
    private lateinit var getAnimeWithCategoriesUseCase :FakeGetAnimeWithCategoriesUseCase

    @Mock
    private lateinit var observer: Observer<HomeUiState>

    private val animeHomeUiMapperImpl = AnimeHomeUiMapperImpl()

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(getAnimeWithCategoriesUseCase,animeHomeUiMapperImpl)
        viewModel.animeHomeUiState.observeForever(observer)
    }

    @Test
    fun `when use case returned downloading is live data state downloading`(){
        runBlocking {
            Mockito.`when`(getAnimeWithCategoriesUseCase.invoke(SAMPLE_CATEGORY_NAME))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                })
            viewModel.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            verify(observer).onChanged(HomeUiState.Loading)

        }
    }
    @Test
    fun `when use case returned downloading is live data state success`(){
        runBlocking {
            Mockito.`when`(getAnimeWithCategoriesUseCase.invoke(SAMPLE_CATEGORY_NAME))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Success(animeList))
                })
            viewModel.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            verify(observer).onChanged(HomeUiState.Loading)
            verify(observer).onChanged(HomeUiState.Success(homeUiDataList))

        }
    }
    @Test
    fun `when use case returned downloading is live data state error`(){
        runBlocking {
            Mockito.`when`(getAnimeWithCategoriesUseCase.invoke(SAMPLE_CATEGORY_NAME))
                .thenReturn(flow {
                    emit(NetworkResponseState.Loading)
                    emit(NetworkResponseState.Error(apiException))
                })
            viewModel.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            verify(observer).onChanged(HomeUiState.Loading)
            verify(observer).onChanged(HomeUiState.Error(R.string.error))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun shutdown() {
        Dispatchers.resetMain()
        viewModel.animeHomeUiState.removeObserver(observer)
    }


}