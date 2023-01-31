package com.example.animeapp.domain

import com.example.animeapp.data.dto.Data
import javax.inject.Inject

class AnimeDomainSingleMapperImpl @Inject constructor():AnimeSingleMapper<Data,AnimeEntity> {
    override fun map(input: Data?): AnimeEntity {
        return AnimeEntity(
            id=input?.id.orEmpty(),
            name = input?.attributes?.canonicalTitle.orEmpty(),
            imageUrl = input?.attributes?.posterImage?.medium.orEmpty(),
            description = input?.attributes?.description.orEmpty(),
            popularityRank = input?.attributes?.popularityRank.toString(),
        )
    }
}