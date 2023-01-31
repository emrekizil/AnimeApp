package com.example.animeapp.domain

import com.example.animeapp.data.dto.Data
import javax.inject.Inject

class AnimeDomainListMapperImpl @Inject constructor():AnimeListMapper<Data,AnimeEntity>{
    override fun map(input: List<Data>?): List<AnimeEntity> {
        return input?.map{
            AnimeEntity(
                id=it.id.orEmpty(),
                name = it.attributes?.canonicalTitle.orEmpty(),
                //name = it.attributes?.titles?.enJp.orEmpty(),
                imageUrl = it.attributes?.posterImage?.medium.orEmpty(),
                description = it.attributes?.description.orEmpty(),
                popularityRank = it.attributes?.popularityRank.toString(),
            )
        } ?: emptyList()
    }
}