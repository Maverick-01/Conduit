package com.maverick.api

import com.maverick.api.models.entities.UserCredentials
import com.maverick.api.models.requests.SignUpRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class ConduitClientTest {

    private val conduitClient = ConduitClient()

    @Test
    fun `GET articles`() {
        runBlocking {
            val articles = conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(author = "Anah Benešová")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by favorited`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(favorited = "false")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tag`() {
        runBlocking {
            val articles = conduitClient.api.getArticles(tag = "voluptate")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST user - create new user`() {
        val userCredentials = UserCredentials(
            email = "testmail_${Random.nextInt(999, 9999)}@test.com",
            password = "pass${Random.nextInt(9999, 999999)}",
            username = "random_user_${Random.nextInt(99, 999)}"
        )
        runBlocking {
            val response = conduitClient.api.signUpUser(SignUpRequest(userCredentials))
            assertEquals(userCredentials.username,response.body()?.user?.username)
        }
    }
}