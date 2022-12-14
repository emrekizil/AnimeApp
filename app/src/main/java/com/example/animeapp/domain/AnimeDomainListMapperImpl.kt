package com.example.animeapp.domain

import com.example.animeapp.data.dto.Data
import javax.inject.Inject

class AnimeDomainListMapperImpl @Inject constructor():AnimeListMapper<Data,AnimeEntity>{
    override fun map(input: List<Data>?): List<AnimeEntity> {
        return input?.map{
            AnimeEntity(
                id=it.id,
                name = it.attributes?.titles?.enJp,
                imageUrl = it.attributes?.posterImage?.medium
            )
        } ?: emptyList()
    }
}