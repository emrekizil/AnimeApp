package com.example.animeapp.di

import com.example.animeapp.data.dto.Data
import com.example.animeapp.domain.*
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
    abstract fun bindAnimeDomainListMapper(animeDomainListMapperImpl: AnimeDomainListMapperImpl):AnimeListMapper<Data,AnimeEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeHomeUiMapper(animeHomeUiMapperImpl: AnimeHomeUiMapperImpl):AnimeListMapper<AnimeEntity,HomeUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeDomainSingleMapper(animeDomainSingleMapperImpl: AnimeDomainSingleMapperImpl):AnimeSingleMapper<Data,AnimeEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindAnimeDetailUiMapper(animeDetailUiMapperImpl: AnimeDetailUiMapperImpl) : AnimeSingleMapper<AnimeEntity,DetailUiData>


}