package com.example.recipe

import com.example.recipe.models.*

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf


class RecipeViewModel(private val db: AppDatabase) : ViewModel() {
    private val _recipes = mutableStateListOf<RecipeWithFullIngredients>()
    val recipes: List<RecipeWithFullIngredients> get() = _recipes

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            val list = db.recipeIngredientDao().getAllRecipesWithFullIngredients()
            _recipes.clear()
            _recipes.addAll(list)
        }
    }
}