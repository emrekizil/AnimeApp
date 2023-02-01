package com.example.animeapp.data.repository

import app.cash.turbine.test
import com.example.animeapp.SAMPLE_CATEGORY_NAME
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.mappers.AnimeRepositoryListMapperImpl
import com.example.animeapp.data.mappers.AnimeRepositorySingleMapperImpl
import com.example.animeapp.data.source.RemoteDataSource
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AnimeRepositoryImplTest {
    @Mock
    private lateinit var remoteDataSource:RemoteDataSource
    @Mock
    private lateinit var animeListMapper: AnimeRepositoryListMapperImpl
    @Mock
    private lateinit var animeSingleMapper: AnimeRepositorySingleMapperImpl

    private lateinit var  animeRepositoryImpl: AnimeRepositoryImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        animeRepositoryImpl = AnimeRepositoryImpl(remoteDataSource,animeListMapper=animeListMapper, animeSingleMapper = animeSingleMapper)
    }

    @Test
    fun `when search query family is first state downloading`(){
        runBlocking {
            animeRepositoryImpl.getAnimeWithCategories(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isEqualTo(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
    @Test
    fun `when repository response failure is state downloading and failure sequentially`(){
        runBlocking {
            animeRepositoryImpl.getAnimeWithCategories(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
    @Test
    fun `when repository response success is state downloading and success sequentially`(){
        runBlocking {
            animeRepositoryImpl.getAnimeWithCategories(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

}