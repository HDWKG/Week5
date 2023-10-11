package com.example.week5.model

data class UIState(
    var angka: Int = (1..10).random(),
    var jumlahPercobaan: Int = 3,
    var score: Int = 0,
    var gameOver: Boolean = false
)