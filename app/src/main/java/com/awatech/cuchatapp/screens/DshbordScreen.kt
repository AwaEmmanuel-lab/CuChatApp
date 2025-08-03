package com.awatech.cuchatapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Screens
import com.awatech.cuchatapp.data.recordGrades

@Composable
fun DashBoardScreen(navController: NavHostController, userViewModel: UserViewModel) {

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                }
                Text("Welcome $userName", fontSize = 18.sp)
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.4f),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(color = colorResource(id = R.color.DeepBlue)),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Hello $userName", fontSize = 24.sp, color = Color.White)
                    Text("Grade Analysis and Performance", fontSize = 20.sp, color = Color.White)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp, max = 200.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceEvenly
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
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            GroupCards(navController)
        }
    }
}

@Composable
fun GroupCards(navController: NavHostController) {
    val cardModifier = Modifier
        .fillMaxWidth(0.45f)
        .height(140.dp)

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardCard("Department Group\n\uD83D\uDC65", cardModifier, 140.dp) {
                navController.navigate(Screens.DrawerScreen.chatScreen.route)
            }
            DashboardCard("Level Group", cardModifier, 140.dp) {
                navController.navigate(Screens.DrawerScreen.chatScreenLevel.route)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardCard("CU Group Chat", cardModifier, 140.dp) {
                navController.navigate(Screens.DrawerScreen.chatScreenAll.route)
            }
            DashboardCard("Keep Records of Your Grades", cardModifier, 140.dp) {
                navController.navigate(Screens.DrawerScreen.recordAnalysisScreen.route)
            }
        }
    }
}

@Composable
fun DashboardCard(text: String, modifier: Modifier, height: Dp, onClick: () -> Unit) {
    Card(
        modifier = modifier.height(height),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DeepBlue))
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(8.dp))
        }
    }
}
