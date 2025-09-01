package com.example.myapplication.model

data class Task(
    val name: String,
    val description: String,
    var done: Boolean = false
)
