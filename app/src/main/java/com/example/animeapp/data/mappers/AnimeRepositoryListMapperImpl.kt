package com.example.animeapp.data.mappers

import com.example.animeapp.data.dto.Data
import com.example.animeapp.domain.AnimeEntity
import javax.inject.Inject

class AnimeRepositoryListMapperImpl @Inject constructor(): AnimeListMapper<Data, AnimeEntity> {
    override fun map(input: List<Data>?): List<AnimeEntity> {
        return input?.map{
            AnimeEntity(
                id=it.id.orEmpty(),
                name = it.attributes?.canonicalTitle.orEmpty(),
                imageUrl = it.attributes?.posterImage?.medium.orEmpty(),
                description = it.attributes?.description.orEmpty(),
                popularityRank = it.attributes?.popularityRank.toString(),
            )
        } ?: emptyList()
    }
}