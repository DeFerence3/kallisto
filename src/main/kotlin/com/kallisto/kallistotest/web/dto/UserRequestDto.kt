package com.kallisto.kallistotest.web.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class UserRequestDto(

    @field:NotBlank(message = "Name is required")
    @field:Size(max = 255, message = "Name must be less than 255 characters")
    val name: String,

    @field:Min(-90, message = "Latitude must be between -90 and 90")
    @field:Max(90, message = "Latitude must be between -90 and 90")
    val latitude: Double,

    @field:Min(-180, message = "Longitude must be between -180 and 180")
    @field:Max(180, message = "Longitude must be between -180 and 180")
    val longitude: Double,

    @field:Positive(message = "Service radius must be positive")
    val serviceRadius: Double
)
