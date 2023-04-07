package com.nicolas.freegames.models.domain

data class SystemRequirements(
    val os: String? = null,
    val processor: String? = null,
    val memory: String? = null,
    val graphics: String? = null,
    val storage: String? = null,
)