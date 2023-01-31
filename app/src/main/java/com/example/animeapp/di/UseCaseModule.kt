package com.example.animeapp.di

import com.example.animeapp.domain.GetAnimeWithCategoriesUseCase
import com.example.animeapp.domain.GetAnimeWithCategoriesUseCaseImpl
import com.example.animeapp.domain.GetAnimeWithIdUseCase
import com.example.animeapp.domain.GetAnimeWithIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindGetAnimeUseCase(animeWithCategoriesUseCaseImpl: GetAnimeWithCategoriesUseCaseImpl):GetAnimeWithCategoriesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAnimeSingleUseCase(animeWithIdUseCaseImpl: GetAnimeWithIdUseCaseImpl):GetAnimeWithIdUseCase
}