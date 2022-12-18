package com.obrekht

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Long,
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val isPinned: Boolean = false,
    val isFavorite: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
)
