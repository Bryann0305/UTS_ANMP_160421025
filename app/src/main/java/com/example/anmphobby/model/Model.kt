package com.example.anmphobby.model

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("creator")
    val creator: String?,
    @SerializedName("description_short")
    val descriptionShort: String?,
    @SerializedName("description_long")
    val descriptionLong: String?,
    @SerializedName("image")
    val image: String?,
)
