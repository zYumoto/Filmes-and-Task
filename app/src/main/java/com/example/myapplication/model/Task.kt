package com.example.myapplication.model

data class Task(
    val title: String,
    val description: String,
    var isDone: Boolean = false
)
