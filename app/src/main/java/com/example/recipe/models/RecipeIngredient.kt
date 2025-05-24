package com.example.recipe.models
import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "ingredientId", "measurementId"])
data class RecipeIngredient(
    val recipeId: Long,
    val ingredientId: Long,
    val measurementId: Long,
    val quantity: Float
)