package com.example.animeapp.ui.home

import com.example.animeapp.animeList
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

internal class AnimeHomeUiMapperImplTest {
    private val animeHomeUiMapperImpl = AnimeHomeUiMapperImpl()
    private lateinit var uiElements :List<HomeUiData>

    @Before
    fun setup(){
        uiElements=animeHomeUiMapperImpl.map(animeList)
    }

    @Test
    fun `when entity mapped is id correct`(){
        assertThat(uiElements[0].id).isEqualTo(animeList[0].id)
    }

    @Test
    fun `when entity mapped is name correct`(){
        assertThat(uiElements[0].name).isEqualTo(animeList[0].name)
    }

    @Test
    fun `when entity mapped is image url correct`(){
        assertThat(uiElements[0].imageUrl).isEqualTo(animeList[0].imageUrl)
    }

    @Test
    fun `when entity mapped size of lists same`(){
        assertThat(uiElements.size).isEqualTo(animeList.size)
    }

    @Test
    fun `when input entity is null is result empty`(){
        val newUiElements = animeHomeUiMapperImpl.map(null)
        assert(newUiElements.isEmpty())
    }
}