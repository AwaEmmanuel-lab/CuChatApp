package com.awatech.cuchatapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.Screens
import com.awatech.cuchatapp.ui.theme.CuChaappTheme


@Composable
fun Login(userViewModel: UserViewModel, navController: NavController){

    val loginuserstate = userViewModel.loginUserState.observeAsState().value
    val context = LocalContext.current

    val scrollState = rememberScrollState()


    LaunchedEffect (loginuserstate){
        when(loginuserstate){
            is ResultState.Success -> {Toast.makeText( context,"Login Successful", Toast.LENGTH_LONG).show()
               navController.navigate(Screens.DrawerScreen.dashboard.route){
                    popUpTo(Screens.DrawerScreen.LoginScreen.route){inclusive = true}
                }
            }
            is ResultState.Error -> Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
            else -> {}
        }

    }



    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var matNo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState).background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Image(
                painter = painterResource(id = R.drawable.cu_logo), contentDescription = "cu_logo", modifier = Modifier.aspectRatio(2f)
            )
        }
        Text("Login", fontSize = MaterialTheme.typography.displaySmall.fontSize)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("password") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = matNo,
            onValueChange = { matNo = it },
            label = { Text("password") }
        )
        Row (Modifier.fillMaxWidth().padding(4.dp).aspectRatio(3f)){
            Button(onClick = {
                userViewModel.getMatNo(matNo)
                userViewModel.loginUsers(email, password)

            },
                modifier = Modifier.background(color = Color.Black, shape = RoundedCornerShape(15))
                    .fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(15),
                colors = ButtonColors(Color.Black, Color.White, Color.Transparent, Color.Transparent)
            ) {
                androidx.compose.material.Text(text = "Login", color = Color.White)
            }
        }
        Text("forgotten password? reset password",
            Modifier.clickable {
                //AlertDialog(onDismissRequest = {}, title = {}, text = { Text("reset password") }, confirmButton = {})
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    CuChaappTheme {
       // Login()
    }
}