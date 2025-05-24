package com.example.recipe.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)