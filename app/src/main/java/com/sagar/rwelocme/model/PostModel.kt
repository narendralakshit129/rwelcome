package com.sagar.rwelocme.model

data class PostModel(
    val userName: String,
    val date: String,
    val message: String,
    val imageUrl: String,
    val likes: Int,
    val comments: Int,
    val shares: Int
)