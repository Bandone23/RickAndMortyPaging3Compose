package com.example.rickandmortycompose.domain.usecase

import androidx.paging.PagingData
import com.example.rickandmortycompose.interfaces.RepositoryInterface
import com.example.rickandmortycompose.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: RepositoryInterface) : BaseUseCase<Unit, Flow<PagingData<CharacterModel>>>  {
    override suspend fun execute(input: Unit): Flow<PagingData<CharacterModel>> {
       return repository.getCharacters()
    }

}