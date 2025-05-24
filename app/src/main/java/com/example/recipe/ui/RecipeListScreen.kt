package com.example.recipe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipe.models.RecipeWithFullIngredients
import com.example.recipe.models.Recipe


@Composable
fun RecipeListScreen(
    recipes: List<RecipeWithFullIngredients>,
    onRecipeSelected: (RecipeWithFullIngredients) -> Unit,
    onDelete: (Recipe) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(recipes) { recipeWithFullIngredients ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRecipeSelected(recipeWithFullIngredients) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(recipeWithFullIngredients.recipe.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Ingredients:")
                    recipeWithFullIngredients.ingredients.forEach {
                        Text("- ${it.ingredient.name}: ${it.quantity} ${it.measurement.name}")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Instructions:")
                    Text(recipeWithFullIngredients.recipe.instructions)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { onDelete(recipeWithFullIngredients.recipe) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
