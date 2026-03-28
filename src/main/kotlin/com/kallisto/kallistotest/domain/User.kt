package com.kallisto.kallistotest.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import kotlin.math.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    var latitude: Double,

    var longitude: Double,

    var serviceRadius: Double,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false
) {
    constructor() : this(id = null, name = "", latitude = 0.0, longitude = 0.0, serviceRadius = 0.0)

    fun canService(targetLat: Double, targetLong: Double): Boolean {
        if (isDeleted) return false
        val distance = calculateDistance(targetLat, targetLong)
        return distance <= serviceRadius
    }

    fun calculateDistance(lat2: Double, lon2: Double): Double {
        val lat1: Double = latitude
        val lon1: Double = longitude
        val r = 6371 // avg radius of the earth in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }
}
