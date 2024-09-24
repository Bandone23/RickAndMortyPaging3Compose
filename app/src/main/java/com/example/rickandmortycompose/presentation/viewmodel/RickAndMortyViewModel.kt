package com.example.rickandmortycompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycompose.domain.usecase.GetCharacterUseCase
import com.example.rickandmortycompose.presentation.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val useCase: GetCharacterUseCase) : ViewModel() {

        private val _characters = MutableStateFlow<PagingData<CharacterModel>>(PagingData.empty())
    val characters: MutableStateFlow<PagingData<CharacterModel>> = _characters

  init {

      viewModelScope.launch {
          getCharacters()
      }
  }

    private suspend fun getCharacters() {
        useCase.execute(Unit)
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
            .collect {
                _characters.value = it
            }
    }
}