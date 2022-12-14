package com.example.animeapp.domain

interface AnimeListMapper<I,O>:AnimeMapper<List<I>,List<O>>

interface AnimeMapper <I,O>{
 fun map(input:I?):O
}