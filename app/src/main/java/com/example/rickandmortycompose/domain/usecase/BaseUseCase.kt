package com.example.rickandmortycompose.domain.usecase

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}