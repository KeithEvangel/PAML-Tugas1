package com.sam.tugas1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.activity.ComponentActivity as ComponentActivity1

class MainActivity : ComponentActivity1() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                CalculatorApp()
            }
        }
    }
}