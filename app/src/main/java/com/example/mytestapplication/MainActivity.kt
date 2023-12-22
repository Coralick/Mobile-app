package com.example.mytestapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                NoteScreen()
            }
        }

    }
}

data class Note(val id: Int, val price: String, val category: String = "")
@Composable
fun NoteScreen() {
    val notes = remember { mutableStateListOf<Note>() }

    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally ) {
        var price = remember { mutableStateOf("") }
        var category = remember { mutableStateOf("") }
        Column {
            TextField(
                value = price.value,
                onValueChange = { price.value = it}
            )
            TextField(
                value = category.value,
                onValueChange = { category.value = it}
            )
            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    // Создание новой записи
                    val newNote = Note(
                        id = notes.size + 1,
                        price = price.value,
                        category = category.value
                    )
                    notes.add(newNote)
                    price.value = ""
                    category.value = ""
                }
            ) {
                Text("Создать запись", fontSize = 32.sp)
            }

        }
    }
    Column(
        Modifier
            .verticalScroll(rememberScrollState())) {
        // Отобразить список всех записей
        notes.forEach { note ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .width(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = note.price,  fontSize = 16.sp)
                Text(text = note.category,  fontSize = 16.sp)
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        // Выбрать запись
                        notes.remove(note)
                    }
                ) {
                    Text("Удалить", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNoteScreen() {
    NoteScreen()
}