package com.sam.tugas1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlin.math.round

@Composable
fun CalculatorApp() {
    var display by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var justEvaluated by remember { mutableStateOf(false) }
    var expression by remember { mutableStateOf("") }
    var isTypingSecondOperand by remember { mutableStateOf(false) }

    val buttons = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("C", "0", "=", "+")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Operation bar (ex: 5 + 2)
        Text(
            text = expression,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.End,
            color = Color.Gray,
            fontSize = 24.sp
        )

        // Display result
        Text(
            text = display,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Buttons Grid
        for (row in buttons) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (btn in row) {
                    Button(
                        onClick = {
                            when (btn) {
                                "C" -> {
                                    display = "0"
                                    operand1 = ""
                                    operator = ""
                                    expression = ""
                                    justEvaluated = false
                                    isTypingSecondOperand = false
                                }

                                "=" -> {
                                    if (operand1.isNotEmpty() && operator.isNotEmpty()) {
                                        expression = "$operand1 $operator $display"
                                        display = calculate(operand1, display, operator)
                                        operand1 = display
                                        operator = ""
                                        justEvaluated = true
                                        isTypingSecondOperand = false
                                    }
                                }

                                "+", "-", "*", "/" -> {
                                    if (operator.isNotEmpty() && !isTypingSecondOperand) {
                                        operator = btn
                                        expression = "$operand1 $operator"
                                    } else {
                                        operand1 = display
                                        operator = btn
                                        expression = "$operand1 $operator"
                                        isTypingSecondOperand = true
                                        justEvaluated = false
                                    }
                                }

                                else -> {
                                    display = when {
                                        justEvaluated || display == "0" || isTypingSecondOperand -> btn
                                        else -> display + btn
                                    }
                                    justEvaluated = false
                                    isTypingSecondOperand = false
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .aspectRatio(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                        Text(text = btn, fontSize = 28.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

fun calculate(op1: String, op2: String, op: String): String {
    return try {
        val a = op1.toDouble()
        val b = op2.toDouble()
        val result = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else return "Err"
            else -> 0.0
        }
        if (result % 1 == 0.0) result.toInt().toString() else "%.2f".format(result)
    } catch (e: Exception) {
        "Err"
    }
}