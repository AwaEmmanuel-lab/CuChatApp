package com.awatech.cuchatapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Screens
import com.awatech.cuchatapp.ui.theme.CuChaappTheme

@Composable
fun DashBoardScreen2(navController: NavController, userViewModel: UserViewModel, messageViewModel: MessageViewModel){


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

    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()



    Column (modifier = Modifier.fillMaxSize().verticalScroll(verticalScrollState).background(color = colorResource(id = R.color.coffeColor))){

            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                }
                Text("Welcome $userName", fontSize = 10.sp, color = Color.White)
            }

        Column (modifier = Modifier.fillMaxWidth().aspectRatio(3f).padding(top = 45.dp, bottom = 4.dp)){
            Row (Modifier.fillMaxWidth()
                .aspectRatio(2f)
                .padding(3.dp)
                .background(color = colorResource(id = R.color.lightCream), shape = RoundedCornerShape(8)).border(0.3.dp, Color.Black, RoundedCornerShape(8)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text("Hello \n$userName \uD83D\uDC4B", fontSize = 24.sp, modifier = Modifier.padding(4.dp))
                Image(painter = painterResource(id = R.drawable.boyinabookflying), contentDescription = null, modifier = Modifier.weight(0.5f).aspectRatio(1.5f))
            }
        }
        Column (Modifier.fillMaxWidth().aspectRatio(1.2f).padding(top = 58.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Grade Analysis and Performance", fontSize = 15.sp, fontFamily = FontFamily.Cursive, color = Color.White)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp, max = 200.dp).horizontalScroll(horizontalScrollState),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                gradeListOfNumbers.forEachIndexed { index, value ->
                    val animatedValue = animatableProgress.getOrNull(index)?.value ?: 0f
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .fillMaxHeight(animatedValue)
                                .background(Color.White, shape = RoundedCornerShape(4.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${value.toInt()}", fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }

        Row (Modifier.fillMaxWidth().aspectRatio(2f).padding(top = 20.dp)){
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {
//                    {
//                        messageViewModel.toGetRoomId(currentUser!!.matNo,currentUser!!.level)
//                        navController.navigate(Screens.DrawerScreen.chatScreen.route)
//                    }
                    currentUser?.let{
                        messageViewModel.toGetRoomId(it.matNo, it.level)
                        navController.navigate(Screens.DrawerScreen.chatScreen.route)
                    }
                },
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)
            ){
                Image(painter = painterResource(id = R.drawable.chatmessagelogo), contentDescription = null)
            }
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {
                    currentUser?.let {
                        messageViewModel.getRoomIdForLevel(it.level)
                        navController.navigate(Screens.DrawerScreen.chatScreenLevel.route)
                    }
                },
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)){
                Image(painter = painterResource(id = R.drawable.phonefromcanva), contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
        Row (Modifier.fillMaxWidth().aspectRatio(2f)){
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {
                    navController.navigate(Screens.DrawerScreen.chatScreenAll.route)
                },
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)
            ){
                Image(painter = painterResource(id = R.drawable.alotofpeoplenetworking), contentDescription = null)
            }
            Card (modifier = Modifier.weight(0.45f)
                .padding(4.dp)
                .aspectRatio(1f),
                onClick = {
                    navController.navigate(Screens.DrawerScreen.recordAnalysisScreen.route)
                },
                elevation = CardDefaults.cardElevation(8.dp, 0.dp)){
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
