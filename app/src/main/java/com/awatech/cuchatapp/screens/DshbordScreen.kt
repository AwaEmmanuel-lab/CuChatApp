package com.awatech.cuchatapp.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Screens
import com.awatech.cuchatapp.data.recordGrades

@Composable
fun DashBoardScreen(navController: NavHostController, userViewModel: UserViewModel){


    val currentUser by userViewModel.getCurrentUser.observeAsState()
    val gradeliststate by userViewModel.getAllGradesList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUse()
    }

    var gradeListOfNumbers = gradeliststate.map { it.grade.toFloat() }
    var maxvalue = gradeListOfNumbers.maxOrNull() ?: 0f
    var animatableprogress = remember{ gradeListOfNumbers.map { Animatable(0f) } }

    LaunchedEffect(gradeliststate) {
        animatableprogress.forEachIndexed { index, animatable ->
            animatable.animateTo(
                targetValue = (gradeListOfNumbers[index] / maxvalue).toFloat(),
                animationSpec = tween(durationMillis = 800, delayMillis = index * 100)
            )
        }
    }

    val scrollstate = rememberScrollState()


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White).verticalScroll(scrollstate),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
                Text("Welcome ${currentUser?.name}")
            }

            Card (modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.DeepBlue))
                .height(400.dp), elevation = CardDefaults.outlinedCardElevation(8.dp, 8.dp, 10.dp, 12.dp)){

              Column (modifier = Modifier.fillMaxSize().padding(8.dp).background(color = colorResource(id = R.color.DeepBlue)), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.Start){
                  Text("Hello ${currentUser?.name}", fontSize = 30.sp)
                  Text("Grade Analysis and performance", fontSize = 30.sp)
                  Row(
                      modifier = Modifier
                          .padding(16.dp)
                          .fillMaxWidth()
                          .height(200.dp).background(color = Color.Transparent),
                      verticalAlignment = Alignment.Bottom,
                      horizontalArrangement = Arrangement.SpaceEvenly
                  ) {
                      gradeListOfNumbers.forEachIndexed { index, value ->
                          val animatedValue = animatableprogress[index].value
                          Column(
                              horizontalAlignment = Alignment.CenterHorizontally,
                              verticalArrangement = Arrangement.Bottom,
                              modifier = Modifier.fillMaxHeight().background(color = Color.Transparent)
                          ) {
                              Box(
                                  modifier = Modifier
                                      .width(30.dp)
                                      .fillMaxHeight(animatedValue)
                                      .background(Color(0xFFF7F7FA), shape = RoundedCornerShape(4.dp))
                              )
                              Spacer(modifier = Modifier.height(8.dp))
                              Text("$value", fontSize = 12.sp)
                          }
                      }
                  }
              }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .background(color = colorResource(id = R.color.DeepBlue)),

                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreen.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Department \nGroup \uD83D\uDC65", fontSize = 24.sp, color = Color.White, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                    )
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .background(color = colorResource(id = R.color.DeepBlue)),
                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreenLevel.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Level Group", fontSize = 24.sp, color = Color.White, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally))
                }

            }

            Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .background(color = colorResource(id = R.color.DeepBlue)),
                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreenAll.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Cu Group Chat", fontSize = 24.sp, color = Color.White,modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally))
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .width(180.dp)
                        .background(color = colorResource(id = R.color.DeepBlue)),
                    onClick = {
                        navController.navigate(Screens.DrawerScreen.recordAnalysisScreen.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("keep Records of your Grades", fontSize = 24.sp, color = Color.White,modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally))
                }

            }
        }
    }
}


