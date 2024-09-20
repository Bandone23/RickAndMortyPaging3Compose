package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class ResponseEntry(
    @SerializedName("info") val information:InfoResponseEntry,
    @SerializedName("results") val results:List<CharacterResponseEntry>,
)
