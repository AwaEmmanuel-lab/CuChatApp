package com.awatech.cuchatapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.R
import com.awatech.cuchatapp.data.Screens
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(Unit){
        launch { delay(3000)
            navController.navigate(Screens.DrawerScreen.SignUpScreen.route){
                popUpTo("splash"){inclusive = true}
            }
        }
    }


    Column (modifier = Modifier
        .fillMaxSize().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.cu_logo), contentDescription = "cu_logo"
        )
    }
}




@Composable
fun CarouselScreen(navController: NavHostController){

    val images = listOf(
        R.drawable.student,
        R.drawable.padlock
    )
    val name = listOf("student", "secure")

    val pagerState = androidx. compose. foundation.pager.rememberPagerState(0, 0f, { images.size })
    val pagerState2 = com.google.accompanist.pager.rememberPagerState()

    Column (
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        ) {
            page ->
            Image(painter = painterResource(id = images[page]), contentDescription = name[page], modifier = Modifier.fillMaxSize())
        }
        HorizontalPagerIndicator(
            pagerState = pagerState2,
            modifier = Modifier.padding(8.dp)
        )
    }
}