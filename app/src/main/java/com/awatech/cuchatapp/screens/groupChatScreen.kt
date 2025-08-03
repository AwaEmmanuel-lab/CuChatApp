package com.awatech.cuchatapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.data.Message
import com.awatech.cuchatapp.ui.theme.CuChaappTheme

@Composable
fun GroupChatScreen(navController: NavHostController, messageViewModel: MessageViewModel){

    val messages by messageViewModel.getMessage.observeAsState(emptyList())
    val currentUser by messageViewModel.curreUserState.observeAsState()


    LaunchedEffect (Unit){
        messageViewModel.getMessage(messageViewModel.roomId.value)
        messageViewModel.getcurrentUser()
    }

   Scaffold (
       topBar = {
           TopAppBar(navController, messageViewModel.curreUserState.value!!.course)
       }
   ){
       var message by remember {mutableStateOf("")}
       Column (Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           LazyColumn (modifier = Modifier.fillMaxWidth()
               .weight(1f)
           ){
               items(messageViewModel.getMessage.value){
                   message ->
                   var msg = message.copy(isCurrentUser = messageViewModel.curreUserState.value!!.matNo == message.matNo)
                   MessageItem(msg, messageViewModel)
               }
           }
           Row (modifier = Modifier.fillMaxWidth().padding(it), horizontalArrangement = Arrangement.SpaceEvenly){
               TextField(value = message, onValueChange = { message = it}, modifier = Modifier.weight(1f) )
               IconButton(onClick = {
                   messageViewModel.sendMessage(message, messageViewModel.roomId.value)
               }) {
                   Icon(imageVector = Icons.Default.Send, contentDescription = null)
               }
           }

       }
   }
}


@Composable
fun MessageItem(message: Message, messageViewModel: MessageViewModel){

    val currentUserState by messageViewModel.curreUserState.observeAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isCurrentUser){
            Alignment.End
        }else{
            Alignment.Start
        }
    ) {
        Box (modifier = Modifier.background(color = if(message.isCurrentUser){Color.Black}else{Color.DarkGray})){
            Text(message.text, color = Color.White)
            Text(messageViewModel.convertTimeStamp(message.timestamp), color = Color.White)
            messageViewModel.curreUserState.value?.name?.let { Text("From" + it) }
        }
    }
}


@Composable
fun TopAppBar(navController: NavHostController, course: String){

    androidx.compose.material.TopAppBar(
        title = {
            Text("$course Group Chat")
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        elevation = 8.dp,
        backgroundColor = Color.Black,
        contentColor = Color.White,
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val fakeNavController = rememberNavController()
    CuChaappTheme {
        //GroupChatScreen(fakeNavController)
    }
}