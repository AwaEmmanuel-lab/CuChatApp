package com.awatech.cuchatapp.ViewModels


import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awatech.cuchatapp.Repository.MessageRepository
import com.awatech.cuchatapp.Repository.UserAuthRepository
import com.awatech.cuchatapp.data.Injection
import com.awatech.cuchatapp.data.Message
import com.awatech.cuchatapp.data.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MessageViewModel: ViewModel() {

    val messages = MessageRepository(Injection.getinstance())
    val user = UserAuthRepository(Injection.getinstance(), FirebaseAuth.getInstance())


    private val _getMessage = MutableLiveData<List<Message>>()
    val getMessage: LiveData<List<Message>> = _getMessage

    private var _currentUserState = MutableLiveData<User?>()
    var curreUserState: LiveData<User?> = _currentUserState

    fun getcurrentUser(){
        viewModelScope.launch{
            _currentUserState.value = user.getCurrentUser()
        }
    }

    fun sendMessage(text: String){
        viewModelScope.launch {
            var message = Message(
                text = text,
                username = curreUserState.value?.name ?: "Student",
                textId = "",
                matNo = _currentUserState.value?.matNo ?: "invalid",
                timestamp = Timestamp.now(),
                isCurrentUser = true,
            )
            messages.sendMessage(message, _roomId.value)
        }
    }



    fun getMessage(){
        viewModelScope.launch {
            messages.getMessage(_roomId.value).collect{
                msg -> _getMessage.value = msg
            }
        }
    }

    private var _getmessagelist2 = MutableLiveData<List<Message>>()
    var getmessagelist2: LiveData<List<Message>> = _getmessagelist2

    fun sendMessagetogroup2(text: String){
        viewModelScope.launch {
            var message = Message(
                text = text,
                username = curreUserState.value?.name ?: "Student",
                textId = "",
                timestamp = Timestamp.now(),
                matNo = _currentUserState.value?.matNo ?: "Invalid",
                isCurrentUser = false
            )

            messages.sendmessages2(message, roomIdForLevel.value)
        }
    }

    fun getMessage2(){
        viewModelScope.launch {
            messages.getMessages2(roomIdForLevel.value).collect{
                    msg -> _getmessagelist2.value = msg
            }
        }
    }


    private var _getmessagelist3 = MutableLiveData<List<Message>>()
    var getmessagelist3: LiveData<List<Message>> = _getmessagelist3

    fun sendMessagetogroup3(text: String){
        viewModelScope.launch {
            var message = Message(
                text = text,
                username = curreUserState.value?.name ?: "Student",
                textId = "",
                timestamp = Timestamp.now(),
                matNo = _currentUserState.value?.matNo ?: "Invalid",
                isCurrentUser = false
            )

            messages.sendmessages3(message, "all")
        }
    }

    fun getMessage3(){
        viewModelScope.launch {
            messages.getMessages3("all").collect{
                    msg -> _getmessagelist3.value = msg
            }
        }
    }



    fun convertTimeStamp(timestamp: Timestamp):String{
        val date = timestamp.toDate()
        val format = SimpleDateFormat("dd/MM/yyyy, HH:mm:ss a", Locale.getDefault())
        val formattedDate = format.format(date)
        return formattedDate
    }

    private var _roomId = mutableStateOf("")
    var roomId: State<String> = _roomId
    fun toGetRoomId(matNo: String, level: String){
        var roomId: String = ""
        for (i in 2..3){
            roomId += matNo[i]
        }
        roomId += level[0]
        _roomId.value = roomId
    }

    private var _roomIdForLevel = mutableStateOf("")
    var roomIdForLevel: State<String> = _roomIdForLevel

    fun getRoomIdForLevel(level: String){
        _roomIdForLevel.value = "${level[0]}"
    }

}