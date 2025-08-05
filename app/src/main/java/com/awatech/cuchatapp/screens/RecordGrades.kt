package com.awatech.cuchatapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.recordGrades
import com.google.type.ColorOrBuilder

@Composable
fun RecordGradesScreen(userViewModel: UserViewModel){

    val gradeliststate by userViewModel.getAllGradesList.observeAsState(emptyList())
    var nameOfCourse by remember { mutableStateOf("") }
    var Grades by remember { mutableStateOf("") }

    LaunchedEffect (Unit){
        userViewModel.getAllGrades()
    }

    Column (modifier = Modifier.fillMaxSize().background(Color.White)){
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp).weight(1f)) {
            items(gradeliststate) { grade ->
                gradeItem(grade, userViewModel)
            }
        }
        Row (modifier = Modifier.fillMaxWidth().padding()){
            OutlinedTextField(
                value = nameOfCourse,
                onValueChange = {nameOfCourse = it},
                modifier = Modifier.weight(1f),
                label = {Text("Course")}
            )
            OutlinedTextField(
                value = Grades,
                onValueChange = {Grades = it},
                modifier = Modifier.weight(1f),
                label = {Text("Your Score")}
            )
            Button(onClick = {
                userViewModel.setGrades(nameOfCourse, Grades)
            }, modifier = Modifier.padding(8.dp).weight(1f)) {
                Text("Add Scores ")
            }
        }
    }
}


//@Composable
//fun gradeItem(){
//    Card (modifier = Modifier.fillMaxWidth().padding(top = 8.dp), elevation = 8.dp, backgroundColor = colorResource(id = R.color.DeepBlue), contentColor = Color.White){
//        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
//            Text(record.nameOfCourse, color = Color.White)
//            Text(record.grade, color = Color.White)
//            Button(onClick = {
//                userViewModel.deleteGrade(record.id)
//            }) {
//                Icon(Icons.Default.Delete, contentDescription = "Delete Icon")
//            }
//        }
//    }
//}

@Composable
fun gradeItem(record: recordGrades, userViewModel: UserViewModel) {

        Row(Modifier.fillMaxWidth().aspectRatio(5f).padding(4.dp)) {
            Card(modifier = Modifier.fillMaxWidth()
                .aspectRatio(5f),
                elevation = 8.dp
            ) {
                Row (Modifier.fillMaxSize().padding(8.dp).background(color = colorResource(id = R.color.lightGrren)), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    Text("${record.nameOfCourse}:\t\t${record.grade} ")
                    IconButton(onClick = {
                        userViewModel.deleteGrade(record.id)
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }


}

