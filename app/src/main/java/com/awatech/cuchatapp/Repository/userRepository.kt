package com.awatech.cuchatapp.Repository

import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.User
import com.awatech.cuchatapp.data.recordGrades
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserAuthRepository(val firestore: FirebaseFirestore, val auth: FirebaseAuth) {

    suspend fun saveUser(user: User, uid: String): ResultState<Boolean> =
        try{
            firestore.collection("Users").document(uid).set(user).await()
            ResultState.Success(true)
        }catch(e: Exception){
            ResultState.Error(e)
        }




    suspend fun registerUser( name: String, email: String, password: String, matNo: String, course: String,yearOfGraduation: String, level: String): ResultState<Boolean> =
        try{
            var result = auth.createUserWithEmailAndPassword(email,password).await()
            val user = User(name, email, password, matNo, course, yearOfGrad = yearOfGraduation, level = level)
            val uid = result.user?.uid ?: throw IllegalStateException("User UID is null")
            saveUser(user, uid)
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }

    suspend fun loginuser(email: String, password: String): ResultState<Boolean> {
        return try{
            auth.signInWithEmailAndPassword(email, password).await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }
    }

    fun getAllUsers(): Flow<List<User>> = callbackFlow {
        val subscription =  firestore.collection("Users").addSnapshotListener{ snapshot, _ ->
            snapshot?.let{
               trySend(it.documents.mapNotNull { doc -> doc.toObject(User:: class.java)?.copy() }).isSuccess
            }
        }
        awaitClose { subscription.remove() }
    }

    var matNoForId : String = ""

    suspend fun getCurrentUser(): User?{

        val uid = auth.currentUser?.uid ?: throw IllegalStateException("User UID is null")

                val snapshot = firestore.collection("Users").document(uid).get().await()
                val currentuserdetail =  snapshot.toObject(User::class.java)
                return currentuserdetail

    }

    suspend fun sendGrades(records: recordGrades):ResultState<Boolean> =
        try{
            firestore.collection("Records").add(records).await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }

    fun getAllRecords():Flow<List<recordGrades>> = callbackFlow {
        val subscription = firestore.collection("Records").addSnapshotListener{
            snapshot, _ ->
            snapshot?.let{
                trySend(
                    it.documents.mapNotNull { doc -> doc.toObject(recordGrades::class.java)?.copy(id = doc.id) }
                ).isSuccess
            }
        }
        awaitClose { subscription.remove() }
    }

    suspend fun deleteRecord(id: String):ResultState<Boolean> =
        try{
            firestore.collection("Records").document(id).delete().await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }
}