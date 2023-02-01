package com.example.animeapp.data.api

import com.example.animeapp.SAMPLE_CATEGORY_NAME
import com.example.animeapp.SAMPLE_CATEGORY_RESPONSE_FILE_NAME
import com.example.animeapp.SAMPLE_ID
import com.example.animeapp.SAMPLE_SINGLE_ANIME_RESPONSE_FILE_NAME
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeApiTest {

    private lateinit var api: AnimeApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup(){
        mockWebServer.start(3000)
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }

    @Test
    fun when_searchQueryFamily_is_response_NotNull(){
        runBlocking {
            enqueueResponse(SAMPLE_CATEGORY_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()

        }
    }

    @Test
    fun `when search id 45745 is response not null`(){
        runBlocking {
            enqueueResponse(SAMPLE_SINGLE_ANIME_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithId(SAMPLE_ID)
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun `when search query family is request path same`(){
        runBlocking {
            enqueueResponse(SAMPLE_CATEGORY_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/anime?filter%5Bcategories%5D=$SAMPLE_CATEGORY_NAME")
        }
    }

    @Test
    fun `when search id 45745 is request path same`(){
        runBlocking {
            enqueueResponse(SAMPLE_SINGLE_ANIME_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithId(SAMPLE_ID)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/anime/$SAMPLE_ID")
        }
    }

    @Test
    fun `when search query family is first item id expected`(){
        runBlocking {
            enqueueResponse(SAMPLE_CATEGORY_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithCategories(SAMPLE_CATEGORY_NAME)
            val firstItem= response.data!!.first()
            assertThat(firstItem.id).isEqualTo("452")
        }
    }

    @Test
    fun `when search id 45745 is item id expected`(){
        runBlocking {
            enqueueResponse(SAMPLE_SINGLE_ANIME_RESPONSE_FILE_NAME)
            val response = api.getAnimeWithId(SAMPLE_ID)
            val itemId = response.data!!.id
            assertThat(itemId).isEqualTo("45745")
        }
    }


    private fun enqueueResponse(fileName:String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

}