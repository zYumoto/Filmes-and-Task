package com.example.meusfilmes.model

data class Movie(
    val title: String,
    val director: String,
    val genres: List<String>,
    val rating: Int
)
