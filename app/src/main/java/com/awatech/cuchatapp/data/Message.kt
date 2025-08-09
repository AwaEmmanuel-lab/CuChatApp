package com.awatech.cuchatapp.data

import com.google.firebase.Timestamp

data class Message(
    var text: String = "",
    var timestamp: Timestamp = Timestamp.now(),
    var username: String = "",
    var textId: String = "",
    var matNo: String = "",
    var senderName: String = "",
    var isCurrentUser: Boolean = false
)
