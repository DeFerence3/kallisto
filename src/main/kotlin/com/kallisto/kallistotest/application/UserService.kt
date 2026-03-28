package com.kallisto.kallistotest.application

import com.kallisto.kallistotest.domain.User
import com.kallisto.kallistotest.domain.UserRepository
import com.kallisto.kallistotest.mapper.UserMapper
import com.kallisto.kallistotest.mapper.UserMapper.toEntity
import com.kallisto.kallistotest.web.dto.UserRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.to

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> {
        return userRepository.findByIsDeletedFalse()
    }

    @Transactional
    fun registerUser(user: UserRequestDto): User {
        return userRepository.save(user.toEntity())
    }

    @Transactional
    fun updateUser(id: Long, updatedUser: User): User {
        val existingUser = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with id: $id") }
        
        if (existingUser.isDeleted) {
            throw NoSuchElementException("User not found with id: $id")
        }

        existingUser.name = updatedUser.name
        existingUser.latitude = updatedUser.latitude
        existingUser.longitude = updatedUser.longitude
        existingUser.serviceRadius = updatedUser.serviceRadius

        return userRepository.save(existingUser)
    }

    @Transactional
    fun softDeleteUser(id: Long) {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with id: $id") }
        user.isDeleted = true
        userRepository.save(user)
    }

    fun searchProviders(lat: Double, lon: Double): List<User> {
        return userRepository.findByIsDeletedFalse()
            .filter { it.canService(lat, lon) }
    }
}
