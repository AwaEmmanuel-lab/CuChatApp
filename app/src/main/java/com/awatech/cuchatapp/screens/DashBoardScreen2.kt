package com.awatech.cuchatapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.ui.theme.CuChaappTheme

@Composable
fun DashBoardScreen2(navController: NavHostController, userViewModel: UserViewModel){


    val currentUser by userViewModel.getCurrentUser.observeAsState()
    val gradeliststate by userViewModel.getAllGradesList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUse()
        userViewModel.getAllGrades()
    }

    val userName = currentUser?.name ?: "Student"
    val gradeListOfNumbers = gradeliststate.mapNotNull { it.grade.toFloatOrNull() }
    val maxvalue = gradeListOfNumbers.maxOrNull()?.takeIf { it > 0 } ?: 1f
    val animatableProgress = remember { gradeListOfNumbers.map { Animatable(0f) } }

    LaunchedEffect(gradeliststate) {
        animatableProgress.forEachIndexed { index, animatable ->
            animatable.animateTo(
                targetValue = gradeListOfNumbers[index] / maxvalue,
                animationSpec = tween(durationMillis = 800, delayMillis = index * 100)
            )
        }
    }





    Column (modifier = Modifier.fillMaxSize()){

            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                }
                Text("Welcome username", fontSize = 18.sp)
            }

        Column (modifier = Modifier.fillMaxWidth().aspectRatio(3f).padding(top = 45.dp, bottom = 4.dp)){
            Row (Modifier.fillMaxWidth()
                .aspectRatio(2f)
                .background(color = colorResource(id = R.color.lightCream)).border(0.3.dp, Color.Black),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text("Hello \nusername \uD83D\uDC4B", fontSize = 34.sp, modifier = Modifier.padding(4.dp))
                Image(painter = painterResource(id = R.drawable.boyinabookflying), contentDescription = null, modifier = Modifier.weight(0.5f).aspectRatio(1.5f))
            }
        }
        Column (Modifier.fillMaxWidth().aspectRatio(1.2f).padding(top = 58.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Grade Analysis and Performance", fontSize = 20.sp, fontFamily = FontFamily.Cursive)
        }
        Row (Modifier.fillMaxWidth().aspectRatio(2f)){
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {},
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)
            ){
                Image(painter = painterResource(id = R.drawable.booksfrocanva), contentDescription = null)
            }
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {},
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)){
                Image(painter = painterResource(id = R.drawable.phonefromcanva), contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Row (Modifier.fillMaxWidth().aspectRatio(2f)){
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {},
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)
            ){
                Image(painter = painterResource(id = R.drawable.alotofpeoplenetworking), contentDescription = null)
            }
            Card (modifier = Modifier.weight(0.45f).padding(4.dp).aspectRatio(1f), onClick = {}, elevation = CardDefaults.cardElevation(8.dp, 0.dp)){
                Image(painter = painterResource(id = R.drawable.booksfrocanva), contentDescription = null)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DashBoardScreen2Preview(){
    CuChaappTheme{
       // DashBoardScreen2()
    }
}
