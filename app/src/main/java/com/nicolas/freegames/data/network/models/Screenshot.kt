package com.nicolas.freegames.data.network.models


import com.google.gson.annotations.SerializedName

data class Screenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)