package com.example.recipe.models
import androidx.room.Embedded


data class IngredientWithMeasurementAndQuantity(
    @Embedded val ingredient: Ingredient,
    val quantity: Float,
    @Embedded(prefix = "measurement_") val measurement: Measurement
)

data class RecipeWithFullIngredients(
    @Embedded val recipe: Recipe,
    val ingredients: List<IngredientWithMeasurementAndQuantity>
)