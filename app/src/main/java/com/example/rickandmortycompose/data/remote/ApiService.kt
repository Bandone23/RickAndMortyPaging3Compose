package com.example.rickandmortycompose.data.remote

import com.example.rickandmortycompose.data.model.ResponseEntry
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int): ResponseEntry
}