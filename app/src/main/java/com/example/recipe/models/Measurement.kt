package com.example.recipe.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Measurement(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Float
)