package com.kallisto.kallistotest

import com.kallisto.kallistotest.domain.User
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserDomainTest {

    @Test
    fun `test canService - within radius`() {
        // Los Angeles Node
        val user = User(
            name = "LA Node",
            latitude = 34.0522,
            longitude = -118.2437,
            serviceRadius = 50.0
        )

        // Santa Monica is approx 24km away
        assertTrue(user.canService(34.0195, -118.4912))
    }

    @Test
    fun `test canService - outside radius`() {
        // Los Angeles Node
        val user = User(
            name = "LA Node",
            latitude = 34.0522,
            longitude = -118.2437,
            serviceRadius = 10.0
        )

        // Santa Monica is approx 24km away, should be outside
        assertFalse(user.canService(34.0195, -118.4912))
    }

    @Test
    fun `test canService - soft deleted user`() {
        val user = User(
            name = "LA Node",
            latitude = 34.0522,
            longitude = -118.2437,
            serviceRadius = 50.0,
            isDeleted = true
        )

        assertFalse(user.canService(34.0522, -118.2437))
    }
}
