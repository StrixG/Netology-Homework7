package com.obrekht

object WallService {
    private var incrementId = 0;

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = ++incrementId)
        posts += newPost

        return newPost
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst { post.id == it.id }
        if (index >= 0) {
            val foundPost = posts[index]
            posts[index] = post.copy(
                id = foundPost.id,
                ownerId = foundPost.ownerId,
                fromId = foundPost.fromId,
                date = foundPost.date
            )

            return true
        }

        return false
    }

    fun clear() {
        posts = emptyArray()
        incrementId = 0
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        posts.find { it.id == postId } ?: throw PostNotFoundException(postId)
        comments += comment

        return comment
    }
}