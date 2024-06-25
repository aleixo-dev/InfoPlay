package com.nicolas.freegames.data.network.models


import com.google.gson.annotations.SerializedName

data class MinimumSystemRequirements(
    @SerializedName("graphics")
    val graphics: String,
    @SerializedName("memory")
    val memory: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("processor")
    val processor: String,
    @SerializedName("storage")
    val storage: String
)