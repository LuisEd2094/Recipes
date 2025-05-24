package com.example.recipe.models
import androidx.room.*

@Dao
interface RecipeIngredientDao {

    @Insert
    suspend fun insert(recipeIngredient: RecipeIngredient)

    @Transaction
    @Query("""
    SELECT 
        i.id, i.name, 
        ri.quantity,
        m.id AS measurement_id,
        m.name AS measurement_name,
        m.quantity AS measurement_quantity
    FROM RecipeIngredient ri
    INNER JOIN Ingredient i ON ri.ingredientId = i.id
    INNER JOIN Measurement m ON ri.measurementId = m.id
    WHERE ri.recipeId = :recipeId
""")
    suspend fun getIngredientsForRecipe(recipeId: Long): List<IngredientWithMeasurementAndQuantity>


    @Transaction
    suspend fun getAllRecipesWithFullIngredients(): List<RecipeWithFullIngredients> {
        val recipes = getAllRecipes()
        return recipes.map { recipe ->
            val ingredients = getIngredientsForRecipe(recipe.id)
            RecipeWithFullIngredients(recipe, ingredients)
        }
    }

    @Query("SELECT * FROM Recipe")
    suspend fun getAllRecipes(): List<Recipe>
}