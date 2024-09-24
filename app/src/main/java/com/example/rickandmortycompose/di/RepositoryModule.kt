package com.example.rickandmortycompose.di

import com.example.rickandmortycompose.data.repository.CharacterRepository
import com.example.rickandmortycompose.data.repository.datasource.CharacterSourceFactory
import com.example.rickandmortycompose.interfaces.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(factory: CharacterSourceFactory): RepositoryInterface {
        return CharacterRepository(factory)
    }

}