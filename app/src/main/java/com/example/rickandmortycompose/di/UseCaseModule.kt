package com.example.rickandmortycompose.di

import com.example.rickandmortycompose.domain.usecase.GetCharacterUseCase
import com.example.rickandmortycompose.interfaces.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCharacterUseCase(repository: RepositoryInterface): GetCharacterUseCase {
        return GetCharacterUseCase(repository)
    }

}