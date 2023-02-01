package com.example.animeapp.domain

import app.cash.turbine.test
import com.example.animeapp.SAMPLE_CATEGORY_NAME
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.repository.FakeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAnimeWithIdUseCaseImplTest {
    private val fakeRepository = FakeRepository()

    private lateinit var getAnimeWithIdUseCaseImpl :GetAnimeWithIdUseCaseImpl

    @Before
    fun setup(){
        getAnimeWithIdUseCaseImpl= GetAnimeWithIdUseCaseImpl(fakeRepository)
    }

    @Test
    fun `when search query family is first state downloading`(){
        runBlocking {
            getAnimeWithIdUseCaseImpl.invoke(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isEqualTo(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
    @Test
    fun `when repository response failure is state downloading and failure sequentially`(){
        fakeRepository.updateShowError(true)
        runBlocking {
            getAnimeWithIdUseCaseImpl(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
    @Test
    fun `when repository response success is state downloading and success sequentially`(){
        runBlocking {
            getAnimeWithIdUseCaseImpl(SAMPLE_CATEGORY_NAME).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }
}