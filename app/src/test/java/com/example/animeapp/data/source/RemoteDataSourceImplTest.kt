package com.example.animeapp.data.source

import android.view.AbsSavedState
import com.example.animeapp.SAMPLE_CATEGORY_NAME
import com.example.animeapp.animeList
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.api.AnimeApi
import com.example.animeapp.data.dto.AnimeResponse
import com.example.animeapp.dataList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {

    @Mock
    private lateinit var animeApi: AnimeApi

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl


    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(animeApi)
    }

    @Test
    fun `when anime list returned is state success`(){
        runBlocking {
            Mockito.`when`(animeApi.getAnimeWithCategories(SAMPLE_CATEGORY_NAME))
                .thenReturn(
                    AnimeResponse(dataList,null,null)
                )
            val response = remoteDataSourceImpl.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            assertThat(response).isInstanceOf(NetworkResponseState.Success::class.java)
        }

    }

    @Test
    fun `when anime api returned null is state failure`(){
        runBlocking {
            Mockito.`when`(animeApi.getAnimeWithCategories(SAMPLE_CATEGORY_NAME))
                .thenReturn(
                    null
                )
            val response = remoteDataSourceImpl.getAnimeWithId(SAMPLE_CATEGORY_NAME)
            assertThat(response).isInstanceOf(NetworkResponseState.Error::class.java)
        }
    }
}