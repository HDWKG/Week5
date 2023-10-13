package com.example.week5.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week5.model.Course

class soal2ViewModel : ViewModel() {
    private var courseList by mutableStateOf<List<Course>>(emptyList())
    private val totalSKS = mutableFloatStateOf(0f)
    private val ipk = mutableFloatStateOf(0f)
    private val totalSKSxScore = mutableStateOf(0.0)
    fun addCourse(name: String, sks: Float, score: Float) {
        val course = Course(name, sks, score)
        courseList += course
        totalSKS.floatValue += sks
        totalSKSxScore.value += sks * score
        ipk.value = (totalSKSxScore.value / totalSKS.value).toFloat()
    }

    fun getSKS(): MutableFloatState {
        return totalSKS
    }

    fun getCourses(): List<Course> {
        return courseList
    }

    fun getIPK(): MutableFloatState {
        return ipk
    }

    fun removeCourse(course: Course) {
        courseList = courseList.filterNot { it == course }
        totalSKS.value -= course.sks
        totalSKSxScore.value -= course.sks * course.score

        if (totalSKS.value == 0f) {
            ipk.value = 0f
        } else {
            ipk.value = (totalSKSxScore.value / totalSKS.value).toFloat()
        }
    }

    fun addChk(nama: String, score: String, sks: String, context: Context) {
        if (nama.isNotEmpty() && score.isNotEmpty() && sks.isNotEmpty()) {
            val scoreValue = score.toFloatOrNull()
            if (scoreValue != null && scoreValue in 0f..4f) {
                val sksValue = sks.toFloatOrNull()
                addCourse(nama, sksValue ?: 0f, scoreValue)
            } else {
                Toast.makeText(
                    context,
                    "Invalid score. Score should be between 0 and 4.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                "Name/Score/SKS fields cannot be empty.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun tot(): String {
        return if (getSKS().value != null && getSKS().value == 0f) {
            "0"
        } else {
            "%.2f".format(getSKS().value ?: 0f)
        }
    }

    fun ipk(): String {
        return if (getIPK().value != null && getIPK().value == 0f) {
            "0"
        } else {
            "%.2f".format(getIPK().value ?: 0f)
        }
    }

}
