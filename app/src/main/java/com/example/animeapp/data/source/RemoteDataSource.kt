package com.example.animeapp.data.source

import android.provider.ContactsContract
import com.example.animeapp.data.NetworkResponseState
import com.example.animeapp.data.dto.Data

interface RemoteDataSource {
    suspend fun getAnimeWithCategories(categoryQuery:String):NetworkResponseState<List<Data>>
}