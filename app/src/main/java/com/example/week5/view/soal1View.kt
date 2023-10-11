package com.example.week5.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5.ui.theme.Week5Theme
import com.example.week5.viewModel.soal1ViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun soal1View(
    viewModel: soal1ViewModel,
) {
    val gameState by viewModel.uiState.collectAsState()
    var tebakan by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Guess the Number",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        )

        Card(
            modifier = Modifier
                .padding(20.dp)
                .height(350.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Number of Guess: ${gameState.jumlahPercobaan}",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(20.dp)
                        .background(Color(65, 105, 225), shape = RoundedCornerShape(20.dp))
                        .padding(8.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(70.dp))
                    Text(
                        text = "${gameState.angka}",
                        style = TextStyle(fontSize = 50.sp, color = Color.Black),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "From 1 to 10, Guess the number",
                        style = TextStyle(fontSize = 16.sp, color = Color.Black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Score: ${gameState.score}",
                        style = TextStyle(fontSize = 16.sp, color = Color.Black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )

                    CustomTextField(
                        value = tebakan,
                        onValueChanged = { tebakan = it },
                        text = "Enter Your Guess",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                    Button(
                        onClick = {
                            viewModel.check(tebakan)
                            if(gameState.gameOver) {
                                openDialog.value = true
                            }
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Submit")
                    }

                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        viewModel.acakAngka()
                                        viewModel.reset()
                                        openDialog.value = false
                                    },
                                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                                ) {
                                    Text("Play Again", color = Color(65, 105, 225))
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        openDialog.value = false
                                    },
                                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                                ) {
                                    Text("Exit", color = Color(65, 105, 225))
                                }
                            },
                            title = { Text("WELP") },
                            text = { Text("You scored: ${gameState.score}") }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun soal1ViewPreview() {
    Week5Theme {
        val viewModel = soal1ViewModel()
        soal1View(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    )
}
