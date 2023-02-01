package com.example.animeapp.di

import com.example.animeapp.data.dto.Data
import com.example.animeapp.data.mappers.*
import com.example.animeapp.domain.AnimeEntity
import com.example.animeapp.ui.detail.AnimeDetailUiMapperImpl
import com.example.animeapp.ui.detail.DetailUiData
import com.example.animeapp.ui.home.AnimeHomeUiMapperImpl
import com.example.animeapp.ui.home.HomeUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AnimeMapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindAnimeRepositoryListMapper(animeRepositoryListMapperImpl: AnimeRepositoryListMapperImpl): AnimeListMapper<Data, AnimeEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeHomeUiMapper(animeHomeUiMapperImpl: AnimeHomeUiMapperImpl): AnimeListMapper<AnimeEntity, HomeUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeRepositorySingleMapper(animeRepositorySingleMapperImpl: AnimeRepositorySingleMapperImpl): AnimeSingleMapper<Data, AnimeEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeDetailUiMapper(animeDetailUiMapperImpl: AnimeDetailUiMapperImpl) : AnimeSingleMapper<AnimeEntity, DetailUiData>


}