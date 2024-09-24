package com.example.rickandmortycompose.interfaces

import androidx.paging.PagingData
import com.example.rickandmortycompose.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    fun getCharacters(): Flow<PagingData<CharacterModel>>
}