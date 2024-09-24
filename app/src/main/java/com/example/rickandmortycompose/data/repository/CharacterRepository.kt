package com.example.rickandmortycompose.data.repository

import androidx.paging.PagingData
import com.example.rickandmortycompose.data.repository.datasource.CharacterSourceFactory
import com.example.rickandmortycompose.interfaces.RepositoryInterface
import com.example.rickandmortycompose.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val factory: CharacterSourceFactory) :
    RepositoryInterface {
    override fun getCharacters(): Flow<PagingData<CharacterModel>> {
        return factory.getAllCharacters()
    }
}