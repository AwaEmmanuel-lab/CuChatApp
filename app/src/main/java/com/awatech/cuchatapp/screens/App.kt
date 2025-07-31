package com.awatech.cuchatapp.screens

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(navController: NavHostController, userViewModel: UserViewModel, messageViewModel: MessageViewModel){
    NavHost(navController = navController, startDestination = Screens.DrawerScreen.SplashScreen.route) {
        composable(Screens.DrawerScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screens.DrawerScreen.SignUpScreen.route){
            SignUpScreen(navController, userViewModel)
        }
        composable(Screens.DrawerScreen.LoginScreen.route){
            Login(userViewModel, navController)
        }
        composable(Screens.DrawerScreen.CompleteProfileScreen.route + "/{email}/{password}"){ backStackEntry ->
            var encodedemail = backStackEntry.arguments?.getString("email") ?: ""
            var encodedpassword = backStackEntry.arguments?.getString("password")?:""
            var email = Uri.decode(encodedemail)
            var password = Uri.decode(encodedpassword)
            CompleteProfileScreen(email = email, password = password, navController = navController, userViewModel, messageViewModel)
        }
        composable(Screens.DrawerScreen.dashbord.route){
            DashBoardScreen(navController, userViewModel)
        }
        composable(Screens.DrawerScreen.chatScreen.route){
            GroupChatScreen(navController, messageViewModel)
        }
        composable(Screens.DrawerScreen.chatScreenLevel.route) {
            GroupChatScreenForLevel(navController, messageViewModel, userViewModel)
        }
        composable(Screens.DrawerScreen.chatScreenAll.route){
            GroupChatScreenForAll(navController,messageViewModel,userViewModel)
        }
    }
}