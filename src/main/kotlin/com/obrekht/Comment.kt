package com.obrekht

data class Comment(
    val id: Int,
    val fromId: Int,
    val text: String,
    val date: Int = 0
)
