package com.awatech.cuchatapp.screens


import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.Screens
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompleteProfileScreen(email: String,
                          password: String,
                          navController: NavHostController,
                          userViewModel: UserViewModel,
                          messageViewModel: MessageViewModel
                          ){

    val registerState by userViewModel.reagisterUserState.observeAsState()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var matNo by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var yearOfGraduation by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }

    LaunchedEffect (registerState) {
        when(registerState){
            is ResultState.Success -> {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                navController.navigate(Screens.DrawerScreen.dashbord.route){
                    popUpTo(Screens.DrawerScreen.CompleteProfileScreen.route){inclusive = true}
                }
            }
            is ResultState.Error -> {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White).verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Column {
                Image(painter = painterResource(id = R.drawable.completeprofilepage), contentDescription = "excited student", modifier = Modifier.padding(8.dp))
                Text("You are one \n Step away from \n Completing  Your Profile \uD83E\uDD73", fontSize = MaterialTheme.typography.headlineMedium.fontSize, modifier = Modifier.padding(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = name,
            onValueChange = { name = it},
            label = { Text("Name") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = matNo,
            onValueChange = { matNo = it},
            label = { Text("Matriculation Number") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = course,
            onValueChange = { course = it},
            label = { Text("Course") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = yearOfGraduation,
            onValueChange = { yearOfGraduation = it},
            label = { Text("Year of Graduation") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = level,
            onValueChange = { level = it},
            label = { Text("Level") }
        )
        Row (Modifier.fillMaxWidth().padding(8.dp)){
            Button(onClick = {
                if(name.isEmpty()||matNo.isEmpty() || course.isEmpty()||yearOfGraduation.isEmpty()||level.isEmpty()){
                    Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG ).show()
                }else if (userViewModel.verifyAge(matNo.toUpperCase(Locale.ROOT), yearOfGraduation)){
                    Toast.makeText(context, "You are not eligible to use this app", Toast.LENGTH_LONG).show()
                }else{
                    messageViewModel.toGetRoomId(matNo, level)
                    messageViewModel.getRoomIdForLevel(level)
                    userViewModel.registerUser(name, email.toLowerCase(Locale.ROOT), password, matNo.toUpperCase(Locale.ROOT), course.toUpperCase(Locale.ROOT), yearOfGraduation, level)
                }
            },
                modifier = Modifier.background(color = Color.Black, shape = RoundedCornerShape(15))
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15),
                colors = ButtonColors(Color.Black, Color.White, Color.Transparent, Color.Transparent)
            ) {
                Text(text = "Continue", color = Color.White)
            }
        }

    }
}