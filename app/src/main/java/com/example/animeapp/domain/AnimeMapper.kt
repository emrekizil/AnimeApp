package com.example.animeapp.domain


interface AnimeMapper <I,O>{
    fun map(input:I?):O
}
