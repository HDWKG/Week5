package com.example.week5.viewModel

import androidx.lifecycle.ViewModel
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

        _uiState.update {dataStateYangSekarang ->
            dataStateYangSekarang.copy(jumlahPercobaan = tmp)
        }
    }

    fun acakAngka() {
        _uiState.value.angka = (1..10).random()
        _uiState.update { currentData ->
            currentData.copy(angka = _uiState.value.angka, jumlahPercobaan = 3, gameOver = false)
        }
    }

    fun tambahScore() {
        val tmp = _uiState.value.score+1
        _uiState.update { currentData ->
            currentData.copy(score = tmp)
        }
//        if (uiState.value.score == 3) {
//            uiState.value.gameOver = true
//        }
    }
}
