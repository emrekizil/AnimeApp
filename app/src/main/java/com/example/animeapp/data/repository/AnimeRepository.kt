package com.example.animeapp.data.repository

import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data

interface AnimeRepository {
     suspend fun getAnimeWithCategories(categoryQuery:String):NetworkResponseState<List<Data>>
}