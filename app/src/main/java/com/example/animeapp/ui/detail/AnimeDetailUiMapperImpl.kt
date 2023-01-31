package com.example.animeapp.ui.detail

import com.example.animeapp.domain.AnimeEntity
import com.example.animeapp.domain.AnimeSingleMapper
import javax.inject.Inject

class AnimeDetailUiMapperImpl @Inject constructor():AnimeSingleMapper<AnimeEntity,DetailUiData> {
    override fun map(input: AnimeEntity?): DetailUiData {
        return DetailUiData(
            input?.name.orEmpty(),input?.imageUrl.orEmpty(),input?.description.orEmpty(),input?.popularityRank.orEmpty()
        )
    }
}