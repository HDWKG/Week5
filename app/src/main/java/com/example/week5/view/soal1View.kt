package com.example.week5.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week5.model.UIState
import com.example.week5.ui.theme.Week5Theme
import com.example.week5.viewModel.soal1ViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun soal1View(
    viewModel: soal1ViewModel
) {
    val gameState by viewModel.uiState.collectAsState()
    var tebakan by remember { mutableStateOf("") }
    var hasilTebakan by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Score: ${gameState.score}")
        if(gameState.score == 1){
            Text(text = "Naik")
        }
        Text("Angka yang ingin ditebak: ${gameState.angka}")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Percobaan tersisa: ${gameState.jumlahPercobaan}")
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
                val tebakanAngka = tebakan.toIntOrNull()
                if (tebakanAngka == gameState.angka) {
                    viewModel.tambahScore()
                    viewModel.acakAngka()
                    hasilTebakan = "Tebakan Anda benar!"
                } else {
                    viewModel.kurangiPercobaan()
                    hasilTebakan = "Tebakan Anda salah."
                }
//                }
//                if (gameState.gameOver) {
//                    hasilTebakan = "Game Over"
//                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Tebak")
        }
        Text(text = hasilTebakan, modifier = Modifier.padding(top = 16.dp))
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
