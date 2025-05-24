package com.example.recipe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipe.models.RecipeWithIngredients

@Composable
fun RecipeListScreen(
    recipes: List<RecipeWithIngredients>,
    onRecipeSelected: (RecipeWithIngredients) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(recipes) { recipeWithIngredients ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRecipeSelected(recipeWithIngredients) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(recipeWithIngredients.recipe.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text("Ingredients:")
                    recipeWithIngredients.ingredients.forEach {
                        Text("- ${it.name}")
                    }
                }
            }
        }
    }
}
