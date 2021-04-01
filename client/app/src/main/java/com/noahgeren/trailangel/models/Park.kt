package com.noahgeren.trailangel.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Park(@PrimaryKey val id: Int = 0, val name: String? = null) {

}
