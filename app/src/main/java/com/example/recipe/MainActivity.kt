package com.example.recipe

import com.example.recipe.models.*
import com.example.recipe.RecipeViewModel.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipe.models.RecipeWithIngredients
import com.example.recipe.ui.AddRecipeScreen
import com.example.recipe.ui.RecipeListScreen
import com.example.recipe.ui.theme.RecipeTheme
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider




class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = DatabaseProvider.getDatabase(applicationContext)

        // Create ViewModel instance here using db
        val recipeViewModel = RecipeViewModel(db)
        enableEdgeToEdge()
        setContent {
            RecipeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Keep track of which screen to show
                    var inner = innerPadding
                    var showAddRecipe by remember { mutableStateOf(false) }
                    val recipes by remember {
                        derivedStateOf { recipeViewModel.recipes }
                    }


                    LaunchedEffect(recipes) {
                        println("Current recipes in DB:")
                        recipes.forEach { recipeWithIngredients ->
                            println("Recipe: ${recipeWithIngredients.recipe.name}")
                            println("Ingredients:")
                            recipeWithIngredients.ingredients.forEach {
                                println("- ${it.name}")
                            }
                        }
                    }

                    if (showAddRecipe) {
                        AddRecipeScreen { recipeName, ingredients, instructions ->
                            lifecycleScope.launch {

                                val recipeId = db.recipeDao().insert(Recipe(name = recipeName, instructions = instructions))
                                ingredients.forEach { (ingredient, measurement) ->
                                    val ingredientId = db.ingredientDao().insert(ingredient)
                                    val measurementId = db.measurementDao().insert(measurement)
                                    db.recipeIngredientDao().insert(
                                        RecipeIngredient(recipeId, ingredientId, measurementId, measurement.quantity)
                                    )
                                }
                                showAddRecipe = false
                            }
                        }
                    } else {
                        RecipeListScreen(
                            recipes = recipes,
                            onRecipeSelected = {
                                // TODO: Handle recipe selection
                            }
                        )
                        // Button to navigate to AddRecipeScreen
                        Button(
                            onClick = { showAddRecipe = true },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Add New Recipe")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeTheme {
        // You can preview your list screen here or add screen
        RecipeListScreen(recipes = emptyList(), onRecipeSelected = {})
    }
}
