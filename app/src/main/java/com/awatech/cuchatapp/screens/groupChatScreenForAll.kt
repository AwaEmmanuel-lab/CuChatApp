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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.awatech.cuchatapp.ViewModels.MessageViewModel
import com.awatech.cuchatapp.ViewModels.UserViewModel
import com.awatech.cuchatapp.data.Message

@Composable
fun GroupChatScreenForAll(navController: NavHostController, messageViewModel: MessageViewModel, userViewModel: UserViewModel){

    val messages by messageViewModel.getmessagelist3.observeAsState(emptyList())
    val currentUser by messageViewModel.curreUserState.observeAsState()


    LaunchedEffect (Unit){
        messageViewModel.getMessage3()
        messageViewModel.getcurrentUser()
    }

    Scaffold (
        topBar = {
            TopAppBar3(navController)
        }
    ){
        var message by remember { mutableStateOf("") }
        Column (
            Modifier.fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LazyColumn (modifier = Modifier.fillMaxWidth()
                .weight(1f)
            ){
                items(messageViewModel.getmessagelist3.value){
                        onemessage ->
                    var msg = onemessage.copy(isCurrentUser = messageViewModel.curreUserState.value?.matNo == onemessage.matNo)
                    MessageItem3(msg, messageViewModel)
                }
            }
            Row (modifier = Modifier.fillMaxWidth().padding(it), horizontalArrangement = Arrangement.SpaceEvenly){
                TextField(value = message, onValueChange = { message = it}, modifier = Modifier.weight(1f) )
                IconButton(onClick = {
                    messageViewModel.sendMessagetogroup3(message)
                }) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                }
            }

        }
    }
}


@Composable
fun MessageItem3(message: Message, messageViewModel: MessageViewModel){

    val currentUserState by messageViewModel.curreUserState.observeAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isCurrentUser){
            Alignment.End
        }else{
            Alignment.Start
        }
    ) {
        Box (modifier = Modifier.background(color = if(message.isCurrentUser){
            Color.Black}else{
            Color.DarkGray})){
            Text(message.text, color = Color.White)
            Text(messageViewModel.convertTimeStamp(message.timestamp), color = Color.White)
            messageViewModel.curreUserState.value?.name?.let { Text("From $it") }
        }
    }
}


@Composable
fun TopAppBar3(navController: NavHostController){

    androidx.compose.material.TopAppBar(
        title = {
            Text("Covenant University Group")
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