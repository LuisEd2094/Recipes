package com.example.recipe.models
import androidx.room.*

@Dao
interface RecipeDao {
    @Transaction
    @Query("""
        SELECT * FROM Recipe
    """)
    suspend fun getAllRecipes(): List<Recipe>

    @Insert
    suspend fun insert(recipe: Recipe): Long

    @Delete
    suspend fun delete(recipe: Recipe)
}