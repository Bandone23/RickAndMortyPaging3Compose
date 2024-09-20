package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class InfoResponseEntry(
    @SerializedName("count") val count:Int,
    @SerializedName("pages") val pages:Int,
    @SerializedName("next") val next:String?,
    @SerializedName("prev") val prev:String?,
)
