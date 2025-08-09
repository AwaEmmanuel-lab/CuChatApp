package com.awatech.cuchatapp.Repository


import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.Message
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageRepository(val firestore: FirebaseFirestore) {

    suspend fun sendMessage(message: Message, id: String): ResultState<Boolean> =

        try{
            firestore.collection("Messages").document(id).collection("message").add(message).await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }

    fun getMessage(id: String): Flow<List<Message>> = callbackFlow {

        val subscription = firestore.collection("Messages"). document(id).collection("message").orderBy("timestamp")
            .addSnapshotListener{ snapshot, e ->
                snapshot?.let {
                    trySend(
                        it.documents.mapNotNull { doc -> doc.toObject(Message::class.java)?.copy(textId = doc.id)}
                    ).isSuccess
                }
        }
        awaitClose { subscription.remove() }
    }

    suspend fun sendmessages2(message: Message, id: String): ResultState<Boolean> =
        try{
            firestore.collection("Messages2").document(id).collection("message").add(message).await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }


    fun getMessages2(id: String): Flow<List<Message>> = callbackFlow {
        val subscription = firestore.collection("Messages2").document(id).collection("message").orderBy("timestamp")
            .addSnapshotListener{snapshot, _ ->
                snapshot?.let{
                    trySend(
                        it.documents.mapNotNull {
                                doc ->
                            doc.toObject(Message::class.java)?.copy(textId = doc.id)
                        }
                    ).isSuccess
                }
            }
        awaitClose { subscription.remove() }
    }

    suspend fun sendmessages3(message: Message, id: String): ResultState<Boolean> =
        try{
            firestore.collection("Messages3").document(id).collection("message").add(message).await()
            ResultState.Success(true)
        }catch (e: Exception){
            ResultState.Error(e)
        }

    suspend fun getMessages3(id: String): Flow<List<Message>> = callbackFlow {
        val subscription = firestore.collection("Messages3").document(id).collection("message").orderBy("timestamp")
            .addSnapshotListener{snapshot, _ ->
                snapshot?.let{
                    trySend(
                        it.documents.mapNotNull {
                                doc ->
                            doc.toObject(Message::class.java)?.copy(textId = doc.id, timestamp = doc.getTimestamp("timestamp")?: Timestamp.now())
                        }
                    ).isSuccess
                }
            }
        awaitClose { subscription.remove() }
    }
}