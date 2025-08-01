package com.awatech.cuchatapp.screens

import android.net.Uri
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.Screens

@Composable
fun SignUpScreen(navController: NavHostController, userViewModel: UserViewModel){
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()


    Column (modifier = Modifier.padding(8.dp)
        .fillMaxSize(). background(color = Color.White).verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
            Image(
                painter = painterResource(id = R.drawable.cu_logo), contentDescription = "cu_logo",modifier = Modifier.aspectRatio(1f)
            )
        }
        //Spacer(modifier = Modifier.height(16.dp))
        Text("Sign up", fontSize = MaterialTheme.typography.displayMedium.fontSize,)
        //Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = email,
            onValueChange = { email = it},
            label = { Text("Email") }
        )
        //Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = password,
            onValueChange = { password = it},
            label = { Text("Password") }
        )
        //Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (email.isEmpty() && password.isEmpty()){
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_LONG).show()
            }else if(userViewModel.verifyEmail(email)){
                Toast.makeText(context, "You can only sign up with a convenant University email", Toast.LENGTH_LONG).show()
            }else{
                var encodedemail = Uri.encode(email)
                var encodedpassword = Uri.encode(password)
                navController.navigate(Screens.DrawerScreen.CompleteProfileScreen.route + "/$encodedemail/$encodedpassword" )
            }
        },
            modifier = Modifier.background(color = Color.Black, shape = RoundedCornerShape(15))
                .fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(15),
            colors = ButtonColors(Color.Black, Color.White,Color.Transparent, Color.Transparent)) {
            Text(text = "Sign Up", color = Color.White)
        }
       // Spacer(modifier = Modifier.height(16.dp))
        Text("Already have an account? Log in",
            modifier = Modifier.clickable {
                navController.navigate(Screens.DrawerScreen.LoginScreen.route)
            })
    }

}



@Composable
fun LoginScreen(){

}