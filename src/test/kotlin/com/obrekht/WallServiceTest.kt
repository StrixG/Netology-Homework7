package com.obrekht

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(
            ownerId = 1,
            fromId = 1,
            text = "Привет!",
            date = System.currentTimeMillis() / 1000
        )

        val resultPost = WallService.add(post)

        assertNotEquals(0, resultPost.id)
    }

    @Test
    fun updateExisting() {
        WallService.add(Post(
            ownerId = 1,
            fromId = 1,
            text = "Пост 1",
            date = System.currentTimeMillis() / 1000
        ))
        val postToUpdate = WallService.add(Post(
            ownerId = 4,
            fromId = 4,
            text = "Пост 2",
            date = System.currentTimeMillis() / 1000
        ))
        WallService.add(Post(
            ownerId = 50,
            fromId = 50,
            text = "Пост 3",
            date = System.currentTimeMillis() / 1000
        ))

        val updatePost = Post(
            id = postToUpdate.id,
            ownerId = 10,
            fromId = 20,
            text = "Обновлённый текст",
            date = System.currentTimeMillis() / 1000
        )

        val result = WallService.update(updatePost)

        assertTrue(result)
    }

    @Test
    fun updateNotExisting() {
        WallService.add(Post(
            ownerId = 1,
            fromId = 1,
            text = "Пост 1",
            date = System.currentTimeMillis() / 1000
        ))
        WallService.add(Post(
            ownerId = 4,
            fromId = 4,
            text = "Пост 2",
            date = System.currentTimeMillis() / 1000
        ))
        WallService.add(Post(
            ownerId = 50,
            fromId = 50,
            text = "Пост 3",
            date = System.currentTimeMillis() / 1000
        ))

        val updatePost = Post(
            id = 100,
            ownerId = 10,
            fromId = 20,
            text = "Обновлённый текст",
            date = System.currentTimeMillis() / 1000
        )

        val result = WallService.update(updatePost)

        assertFalse(result)
    }

    @Test()
    fun addCommentToExistingPost() {
        WallService.add(Post(
            ownerId = 1,
            fromId = 1,
            text = "Пост 1",
            date = System.currentTimeMillis() / 1000
        ))

        val comment = Comment(1, 1, "Comment text")
        val result = WallService.createComment(1, comment)

        assertEquals(comment, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentToUnknownPost() {
        WallService.add(Post(
            ownerId = 1,
            fromId = 1,
            text = "Пост 1",
            date = System.currentTimeMillis() / 1000
        ))

        val comment = Comment(1, 1, "Comment text")
        WallService.createComment(2, comment)
    }
}