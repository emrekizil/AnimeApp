package com.example.animeapp.ui.detail

import com.example.animeapp.animeEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

internal class AnimeDetailUiMapperImplTest {

    private val animeDetailUiMapperImpl = AnimeDetailUiMapperImpl()
    private lateinit var uiElement :DetailUiData

    @Before
    fun setup(){
        uiElement= animeDetailUiMapperImpl.map(animeEntity)
    }

    @Test
    fun `when entity mapped is name correct`(){
        assertThat(uiElement.name).isEqualTo(animeEntity.name)
    }

    @Test
    fun `when entity mapped is description correct`(){
        assertThat(uiElement.description).isEqualTo(animeEntity.description)
    }

    @Test
    fun `when entity mapped is popularity rank correct`(){
        assertThat(uiElement.popularityRank).isEqualTo(animeEntity.popularityRank)
    }

    @Test
    fun `when entity mapped is image url correct`(){
        assertThat(uiElement.imageUrl).isEqualTo(animeEntity.imageUrl)
    }

}