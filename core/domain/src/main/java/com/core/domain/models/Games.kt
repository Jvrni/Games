package com.core.domain.models

data class Games(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String
)
