package com.awatech.cuchatapp.data

data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var matNo: String = "",
    var course: String = "",
    var isCurrentUser: Boolean = false,
    var yearOfGrad: String  = "",
    var level: String = ""
)
