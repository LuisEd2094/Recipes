package com.example.recipe

import com.example.recipe.models.*

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class RecipeViewModel(private val db: AppDatabase) : ViewModel() {
    // This holds the list of recipes your UI will observe
    private val _recipes = mutableStateListOf<RecipeWithIngredients>()
    val recipes: List<RecipeWithIngredients> get() = _recipes

    init {
        loadRecipes()
    }

    fun reload() {
        loadRecipes()
    }
    private fun loadRecipes() {
        viewModelScope.launch {
            val list = db.recipeIngredientDao().getAllRecipesWithIngredients()
            _recipes.clear()
            _recipes.addAll(list)
        }
    }
}
