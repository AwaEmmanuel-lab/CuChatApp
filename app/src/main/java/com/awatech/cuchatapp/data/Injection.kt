package com.awatech.cuchatapp.data

import com.google.firebase.firestore.FirebaseFirestore

object Injection {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun getinstance(): FirebaseFirestore{
        return instance
    }
}