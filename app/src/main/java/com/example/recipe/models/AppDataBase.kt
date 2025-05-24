package com.example.recipe.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context


@Database(entities = [Ingredient::class, Measurement::class, Recipe::class, RecipeIngredient::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun measurementDao(): MeasurementDao
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeIngredientDao(): RecipeIngredientDao
}
object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "recipe_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
