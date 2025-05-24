package com.example.recipe.models
import androidx.room.*

@Dao
interface RecipeIngredientDao {
    @Insert
    suspend fun insert(recipeIngredient: RecipeIngredient)

    @Transaction
    @Query("SELECT * FROM Recipe")
    suspend fun getAllRecipesWithIngredients(): List<RecipeWithIngredients>
}