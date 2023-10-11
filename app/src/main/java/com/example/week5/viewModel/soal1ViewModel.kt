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
        val tmp = _uiState.value.angka
        _uiState.update { currentData ->
            currentData.copy(angka = tmp, jumlahPercobaan = 3, gameOver = false)
        }
    }

    fun tambahScore() {
        val tmp = _uiState.value.score+1
        _uiState.update { currentData ->
            currentData.copy(score = tmp)
        }
    }

    fun check(tebakan: String){
        val tebakanAngka = tebakan.toIntOrNull()
        if (tebakanAngka == _uiState.value.angka) {
            tambahScore()
            acakAngka()
            dead()
        } else {
            kurangiPercobaan()
            dead()
        }
    }

    fun dead(){
        if(_uiState.value.score == 3 || _uiState.value.jumlahPercobaan == 0){
            _uiState.update { currentData ->
                currentData.copy(gameOver = true)
            }
        }
        if(_uiState.value.gameOver){
            _uiState.value.angka = (1..10).random()
            val tmp = _uiState.value.angka
            _uiState.update { currentData ->
                currentData.copy(angka = tmp, jumlahPercobaan = 3, gameOver = false, score = 0)
            }
        }
    }
}
