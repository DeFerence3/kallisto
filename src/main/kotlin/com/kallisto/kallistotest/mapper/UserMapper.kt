package com.kallisto.kallistotest.mapper

import com.kallisto.kallistotest.domain.User
import com.kallisto.kallistotest.web.dto.SearchResponseDto
import com.kallisto.kallistotest.web.dto.UserRequestDto
import lombok.experimental.UtilityClass

object UserMapper {
    fun UserRequestDto.toEntity() = User(
        id = null,
        name = name,
        latitude = latitude,
        longitude = longitude,
        serviceRadius = serviceRadius,
        isDeleted = false
    )
    fun User.toSearchDto(distance: Double) = SearchResponseDto(
        id = id,
        name = name,
        distance = distance
    )
}