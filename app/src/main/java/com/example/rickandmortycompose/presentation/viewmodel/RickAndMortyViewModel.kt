package com.example.rickandmortycompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.rickandmortycompose.data.repository.CharacterRepository
import com.example.rickandmortycompose.presentation.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel@Inject constructor(repository: CharacterRepository): ViewModel()  {
    val characters: Flow<PagingData<CharacterModel>> = repository.getAllCharacters()
}