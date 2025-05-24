package com.example.recipe.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipe.models.Ingredient
import com.example.recipe.models.Measurement


@Composable
fun AddRecipeScreen(
    onSave: (recipeName: String, ingredients: List<Pair<Ingredient, Measurement>>, instructions: String) -> Unit
) {
    var recipeName by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    var ingredientName by remember { mutableStateOf("") }
    var measurementName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    val ingredientList = remember { mutableStateListOf<Pair<Ingredient, Measurement>>() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Recipe Name") }
        )
        Spacer(Modifier.height(8.dp))

        Row {
            TextField(
                value = ingredientName,
                onValueChange = { ingredientName = it },
                label = { Text("Ingredient") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            TextField(
                value = measurementName,
                onValueChange = { measurementName = it },
                label = { Text("Measurement") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))

            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                modifier = Modifier.weight(0.4f),
                singleLine = true,
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (ingredientName.isNotBlank() && measurementName.isNotBlank()) {
                    val quantityFloat = quantity.toFloatOrNull() ?: 0f

                    ingredientList.add(
                        Ingredient(0, ingredientName) to Measurement(0, measurementName, quantityFloat)
                    )
                    ingredientName = ""
                    measurementName = ""
                    quantity = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(Modifier.height(8.dp))

        Text("Ingredients:")
        ingredientList.forEach { (ingredient, measurement) ->
            Text("- ${ingredient.name} (${measurement.name})")
        }

        Spacer(Modifier.height(8.dp))

        TextField(
            value = instructions,
            onValueChange = { instructions = it },
            label = { Text("Instructions") },
            modifier = Modifier.fillMaxWidth().height(120.dp)
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            onSave(recipeName, ingredientList, instructions)
        }) {
            Text("Save Recipe")
        }
    }
}
