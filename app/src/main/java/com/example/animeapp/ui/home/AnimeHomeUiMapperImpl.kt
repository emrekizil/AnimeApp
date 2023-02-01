package com.example.animeapp.ui.home

import com.example.animeapp.domain.AnimeEntity
import com.example.animeapp.data.mappers.AnimeListMapper
import javax.inject.Inject

class AnimeHomeUiMapperImpl @Inject constructor(): AnimeListMapper<AnimeEntity, HomeUiData> {
    override fun map(input: List<AnimeEntity>?): List<HomeUiData> {
        return input?.map {
            HomeUiData(it.id,it.name,it.imageUrl)
        } ?: emptyList()
    }
}