package com.sam.tugas1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun CalculatorApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(text = "Kalkulator Sederhana", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Input Angka 1
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Angka pertama") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input Angka 2
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Angka kedua") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Operasi
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                result = operate(number1, number2, "+")
            }) {
                Text("+")
            }
            Button(onClick = {
                result = operate(number1, number2, "-")
            }) {
                Text("-")
            }
            Button(onClick = {
                result = operate(number1, number2, "*")
            }) {
                Text("×")
            }
            Button(onClick = {
                result = operate(number1, number2, "/")
            }) {
                Text("÷")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Hasil: $result", style = MaterialTheme.typography.titleLarge)
    }
}

fun operate(n1: String, n2: String, op: String): String {
    return try {
        val a = n1.toDouble()
        val b = n2.toDouble()
        val result = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else return "Error: Bagi 0"
            else -> 0.0
        }
        result.toString()
    } catch (e: NumberFormatException) {
        "Input tidak valid"
    }
}