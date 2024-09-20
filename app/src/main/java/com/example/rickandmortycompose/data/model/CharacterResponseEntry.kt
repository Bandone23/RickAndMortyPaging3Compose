package com.example.rickandmortycompose.data.model

import com.example.rickandmortycompose.presentation.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterResponseEntry(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,
) {
    fun toPresentationModel(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            isAlive = status == "Alive",
            image = image,
        )
    }
}
