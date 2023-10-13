package com.example.week5.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5.ui.theme.Week5Theme
import com.example.week5.viewModel.soal2ViewModel

@Composable
fun soal2View(viewModel: soal2ViewModel) {
    var sks by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    val courses = viewModel.getCourses()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            content = {
                item {
                    Text(
                        text = "Courses",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Total SKS: ${
                            viewModel.tot()
                        }",
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = "IPK: ${
                            viewModel.ipk()
                        }",
                        modifier = Modifier.padding(8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        customTextField(
                            value = sks,
                            onValueChanged = { sks = it },
                            text = "SKS",
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                        customTextField(
                            value = score,
                            onValueChanged = { score = it },
                            text = "Score",
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        customTextField(
                            value = nama,
                            onValueChanged = { nama = it },
                            text = "Name",
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                        Button(
                            onClick = {
                                viewModel.addChk(nama, score, sks, context)
                                nama = ""
                                sks = ""
                                score = ""
                            },
                            shape = RoundedCornerShape(40),
                            modifier = Modifier.size(60.dp)
                        ) {
                            Text(
                                text = "+",
                                style = TextStyle(fontSize = 24.sp)
                            )
                        }
                    }
                }
                items(courses) { course ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Name: ${course.name}")
                            Text("IPK: ${"%.2f".format(course.sks)}")
                            Text("Score: ${"%.2f".format(course.score)}")

                            Button(
                                onClick = { viewModel.removeCourse(course) },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(top = 8.dp)
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customTextField(
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
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun soal2Viewpreview() {
    Week5Theme {
        soal2View(viewModel = soal2ViewModel())
    }
}
