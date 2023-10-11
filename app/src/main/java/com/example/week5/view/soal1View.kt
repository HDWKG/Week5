package com.example.week5.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
    var hasilTebakan by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Guess the Number",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )

        Card(
            modifier = Modifier.padding(16.dp),
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue)
                            .padding(8.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "Number of Guess: ${gameState.jumlahPercobaan}",
                            color = Color.White
                        )
                    }
                    Text("${gameState.angka}",style = TextStyle(fontSize = 24.sp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "From 1 to 10 Guess The Number.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Score: ${gameState.score}")
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        value = tebakan,
                        onValueChange = {
                            tebakan = it
                        },
                        textStyle = TextStyle.Default.copy(color = Color.Black),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            viewModel.check(tebakan)
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Tebak")
                    }
                }
                Text(text = hasilTebakan, modifier = Modifier.padding(top = 16.dp))
            })
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
