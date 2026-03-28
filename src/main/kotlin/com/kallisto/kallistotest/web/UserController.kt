package com.kallisto.kallistotest.web

import com.kallisto.kallistotest.application.UserService
import com.kallisto.kallistotest.domain.User
import com.kallisto.kallistotest.web.dto.UserRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "User Management", description = "Endpoints for managing service providers and searching coverage")
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    @Operation(summary = "Fetch all registered users", description = "Returns a list of all active service providers")
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @GetMapping("/search")
    @Operation(summary = "Search providers by coordinates", description = "Returns a list of users whose service radius covers the provided coordinates")
    fun searchProviders(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): List<User> {
        return userService.searchProviders(lat, lon)
    }

    @PostMapping("/users")
    @Operation(summary = "Register a new user", description = "Creates a new service provider with name, lat, long, and service_radius")
    fun registerUser(@Valid @RequestBody user: UserRequestDto): User {
        return userService.registerUser(user)
    }

    @PatchMapping("/users/{id}")
    @Operation(summary = "Edit an existing user's details", description = "Updates an existing service provider's information")
    fun updateUser(@PathVariable id: Long, @Valid @RequestBody user: User): User {
        return userService.updateUser(id, user)
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Remove a service provider", description = "Soft deletes a service provider from the registry")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.softDeleteUser(id)
        return ResponseEntity.noContent().build()
    }
}
