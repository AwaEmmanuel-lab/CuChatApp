package com.awatech.cuchatapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Screens
@Composable
fun DashBoardScreen(navController: NavHostController, userViewModel: UserViewModel){


    val currentUser by userViewModel.getCurrentUser.observeAsState()

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUse()
    }



    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Column (modifier = Modifier.fillMaxWidth().padding(16.dp).background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
                Text("Welcome ${userViewModel.getCurrentUser.value?.name}")
            }

            Card (modifier = Modifier.fillMaxWidth().height(400.dp), elevation = CardDefaults.outlinedCardElevation(8.dp, 8.dp, 10.dp, 12.dp)){

            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
                Card (
                    modifier = Modifier.height(150.dp)
                        .width(180.dp),

                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreen.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Department Group \ngroup\n \uD83D\uDC65", modifier = Modifier.padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                    )
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Card (
                    modifier = Modifier.height(150.dp)
                        .width(180.dp),
                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreenLevel.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Level Group", modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally))
                }

            }

            Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
                Card (
                    modifier = Modifier.height(150.dp)
                        .width(180.dp),
                    onClick = {
                        navController.navigate(Screens.DrawerScreen.chatScreenAll.route)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("Cu Group Chat", modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally))
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Card (
                    modifier = Modifier.height(150.dp)
                        .width(180.dp),
                    onClick = {},
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Text("keep Records of your Grades", modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally))
                }

            }
        }
    }
}


