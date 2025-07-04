package com.example.recipe.models
import androidx.room.*

@Dao
interface IngredientDao {
    @Insert
    suspend fun insert(ingredient: Ingredient): Long
}