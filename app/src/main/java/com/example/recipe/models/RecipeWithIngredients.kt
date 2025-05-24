package com.example.recipe.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithIngredients(
    @Embedded val recipe: Recipe,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = RecipeIngredient::class,
            parentColumn = "recipeId",
            entityColumn = "ingredientId"
        )
    )
    val ingredients: List<Ingredient>
)
