package com.example.calcount

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    // Estado global para almacenar el total de cal
    // orías
    private var totalCalories = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalCountApp()
        }
    }

    // Composable que define toda la UI usando Jetpack Compose
    @Composable
    fun CalCountApp() {
        // Variables de estado para el nombre de la comida y calorías
        var foodName by remember { mutableStateOf("") }
        var foodCalories by remember { mutableStateOf("") }
        var totalCaloriesState by remember { mutableStateOf(totalCalories) }

        // Columnas para organizar los elementos
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Título
            Text("Input the name of the food and the amount of calories", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para el nombre de la comida
            BasicTextField(
                value = foodName,
                onValueChange = { foodName = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                decorationBox = { innerTextField ->
                    if (foodName.isEmpty()) Text("Name of the food", style = MaterialTheme.typography.bodyMedium)
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para las calorías
            BasicTextField(
                value = foodCalories,
                onValueChange = { foodCalories = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                decorationBox = { innerTextField ->
                    if (foodCalories.isEmpty()) Text("Calories", style = MaterialTheme.typography.bodyMedium)
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para agregar la comida
            Button(onClick = {
                val calories = foodCalories.toIntOrNull()
                if (foodName.isNotEmpty() && calories != null) {
                    totalCaloriesState += calories
                    foodName = ""  // Limpiar el campo de nombre
                    foodCalories = ""  // Limpiar el campo de calorías
                } else {
                    Toast.makeText(this@MainActivity, "Por favor ingrese un nombre y las calorías", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add Food")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el total de calorías
            Text("Total Calories: $totalCaloriesState", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
