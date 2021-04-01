package com.noahgeren.trailangel.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trail(@PrimaryKey val id: Int = 0, val name: String? = null, val length: Double? = null, val parkId: Int? = null)
