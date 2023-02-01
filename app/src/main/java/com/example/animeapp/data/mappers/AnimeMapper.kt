package com.example.animeapp.data.mappers


interface AnimeMapper <I,O>{
    fun map(input:I?):O
}
