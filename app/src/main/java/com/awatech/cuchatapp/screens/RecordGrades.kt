package com.awatech.cuchatapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.awatech.cuchatapp.ViewModels.UserViewModel

@Composable
fun RecordGradesScreen(userViewModel: UserViewModel){

    val gradeliststate by userViewModel.getAllGrades.observeAsState()

    Column (modifier = Modifier.fillMaxSize().background(Color.White)){
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp).weight(1f)) {

        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Add Scores ")
        }
    }
}