package com.example.recipe.models
import androidx.room.*

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipe: Recipe): Long

    @Transaction
    @Query("SELECT * FROM Recipe")
    suspend fun getAllRecipes(): List<Recipe>
}