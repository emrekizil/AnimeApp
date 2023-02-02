package com.example.animeapp


import com.example.animeapp.data.dto.Data
import com.example.animeapp.domain.module.AnimeEntity
import com.example.animeapp.ui.detail.DetailUiData
import com.example.animeapp.ui.home.HomeUiData
import com.google.common.annotations.VisibleForTesting

const val SAMPLE_CATEGORY_RESPONSE_FILE_NAME = "AnimeResponse.json"
const val SAMPLE_CATEGORY_NAME= "family"

const val SAMPLE_SINGLE_ANIME_RESPONSE_FILE_NAME= "AnimeSingleResponse.json"
const val SAMPLE_ID = 45745

@VisibleForTesting
val animeEntity = AnimeEntity(
  "45745",
    "Fu",
    "https://media.kitsu.io/anime/45745/poster_image/medium-9bfaa7724851866f6601f1f2c75ec99c.jpeg",
    "Airbnb and TAIKO Studios present the story of a family during Chinese New Year.",
  "18170"
)

@VisibleForTesting
val animeData = Data(
  null,
  "45745",
  null,
  null,
  null
)


@VisibleForTesting
val animeList = mutableListOf(animeEntity)

val dataList = mutableListOf(animeData)

val animeUiData = HomeUiData("45745","Fu","https://media.kitsu.io/anime/45745/poster_image/medium-9bfaa7724851866f6601f1f2c75ec99c.jpeg")

val homeUiDataList = mutableListOf(animeUiData)

val animeDetailUiData = DetailUiData("Fu", "https://media.kitsu.io/anime/45745/poster_image/medium-9bfaa7724851866f6601f1f2c75ec99c.jpeg", "Airbnb and TAIKO Studios present the story of a family during Chinese New Year.", "18170")

val apiException = Exception("Something went wrong")