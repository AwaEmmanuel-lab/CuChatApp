package com.awatech.cuchatapp.data

sealed class Screens(val route: String) {
    sealed class DrawerScreen( droute: String, ) : Screens(droute) {
        object SplashScreen: DrawerScreen("splash")
        object CarouselScreen: DrawerScreen("carousel")
        object SignUpScreen: DrawerScreen("signup")
        object LoginScreen: DrawerScreen("login")
        object CompleteProfileScreen: DrawerScreen("completeprofile")
        object dashboard: DrawerScreen("Dashboard")
        object chatScreen: DrawerScreen("chatScreenCourse")
        object chatScreenLevel: DrawerScreen("chatScreenLevel")
        object chatScreenAll: DrawerScreen("chatScreenAll")
        object recordAnalysisScreen:DrawerScreen("recordAnalysisScreen")
    }
}