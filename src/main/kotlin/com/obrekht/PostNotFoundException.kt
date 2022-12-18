package com.obrekht

class PostNotFoundException(postId: Int) : Exception("Unknown post id: $postId")