package com.example.week5.viewModel

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.R
import com.example.week5.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class soal1ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun kurangiPercobaan() {
        val tmp = _uiState.value.jumlahPercobaan - 1
        _uiState.update { dataStateYangSekarang ->
            dataStateYangSekarang.copy(jumlahPercobaan = tmp)
        }
    }

    fun acakAngka() {
        _uiState.value.angka = (1..10).random()
        val tmp = _uiState.value.angka
        _uiState.update { currentData ->
            currentData.copy(angka = tmp, jumlahPercobaan = 3, gameOver = false)
        }
    }

    fun tambahScore() {
        val tmp = _uiState.value.score + 1
        _uiState.update { currentData ->
            currentData.copy(score = tmp)
        }
    }

    fun reset(){
        _uiState.update { dat ->
            dat.copy(jumlahPercobaan = 3, gameOver = false, score = 0)
        }
    }
    fun check(tebakan: String){
        val tebakanAngka = tebakan.toIntOrNull()
        if (tebakanAngka == _uiState.value.angka) {
            acakAngka()
            tambahScore()
            if (_uiState.value.jumlahPercobaan == 0 || _uiState.value.score == 3) {
                _uiState.value = _uiState.value.copy(gameOver = true)
            }
        } else {
            kurangiPercobaan()
            if (_uiState.value.jumlahPercobaan == 0 || _uiState.value.score == 3) {
                _uiState.value = _uiState.value.copy(gameOver = true)
            }
        }
    }
}
