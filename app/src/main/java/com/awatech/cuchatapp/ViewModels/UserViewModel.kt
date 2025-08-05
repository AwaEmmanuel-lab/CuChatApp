package com.awatech.cuchatapp.ViewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.awatech.cuchatapp.Repository.UserAuthRepository
import com.awatech.cuchatapp.data.Injection
import com.awatech.cuchatapp.data.ResultState
import com.awatech.cuchatapp.data.User
import com.awatech.cuchatapp.data.recordGrades
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class UserViewModel: ViewModel() {

    private val  userAuthRepository = UserAuthRepository(Injection.getinstance(), FirebaseAuth.getInstance())

    private var _savedUserState = MutableLiveData<ResultState<Boolean>>()
    var savedUserState: LiveData<ResultState<Boolean>> = _savedUserState


    private var _registerUserState =  MutableLiveData<ResultState<Boolean>>()
    var reagisterUserState: LiveData<ResultState<Boolean>> = _registerUserState

    fun registerUser(name: String, email: String, password: String, matNo: String, course: String,  yearOfGraduation: String, level: String){
        viewModelScope.launch {
            _registerUserState. value = ResultState.Loading
            _registerUserState.value = userAuthRepository.registerUser(name, email, password, matNo, course, yearOfGraduation, level)
        }
    }

    private var _loginUserState = MutableLiveData<ResultState<Boolean>>()
    var loginUserState: LiveData<ResultState<Boolean>> = _loginUserState

    fun loginUsers(email: String, password: String){
        viewModelScope.launch {
            _loginUserState.value = ResultState.Loading
            _loginUserState.value = userAuthRepository.loginuser(email, password)
        }
    }

    private var _getAllCurrentUser = MutableLiveData<List<User>>()
    var getAllCurrentUser: LiveData<List<User>> = _getAllCurrentUser

    fun getAllCurrrentUser(){
        viewModelScope.launch {
            userAuthRepository.getAllUsers().collect{userlist ->
                _getAllCurrentUser.value = userlist
            }
        }
    }

    fun getMatNo(matNo: String): String{
        userAuthRepository.matNoForId = matNo
        return userAuthRepository.matNoForId
    }

    private var _getCurrentUser = MutableLiveData<User>()
    var getCurrentUser: LiveData<User> = _getCurrentUser

    fun getCurrentUse(){
        viewModelScope.launch {
            _getCurrentUser.value = userAuthRepository.getCurrentUser()
        }
    }


//    fun saveUser(user: User){
//        viewModelScope.launch {
//            _savedUserState.value = ResultState.Loading
//            _savedUserState.value = userAuthRepository.saveUser(user)
//        }
//    }




    fun verifyEmail(email: String): Boolean{
        if (!email.contains("@cu.stu.edu.ng")){
            return true
        }
        return false
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun verifyAge(matNo: String, yearOfGraduation: String): Boolean{
        var yearOfEntry: String = "20"
        for(i in 0..1){
            yearOfEntry += matNo[i]
        }

        if(LocalDate.now().year.toInt() !in yearOfEntry.toInt()..yearOfGraduation.toInt()){
            return true
        }else{
            return false
        }

    }

    private var _getAllGradesList = MutableLiveData<List<recordGrades>>()
    var getAllGradesList: LiveData<List<recordGrades>> = _getAllGradesList

    fun getAllGrades() {
        viewModelScope.launch {
            userAuthRepository.getAllRecords()
                .collect { recordGrades -> _getAllGradesList.value = recordGrades }
        }
    }
    fun setGrades(nameOfcourse: String, grade: String){
        viewModelScope.launch {
            val recordGrades = recordGrades(nameOfcourse,grade, "")
            userAuthRepository.sendGrades(recordGrades)
        }
    }
    private var _recordDeletState = MutableLiveData<ResultState<Boolean>>()
    var recordDeleteState: LiveData<ResultState<Boolean>> = _recordDeletState
    fun deleteGrade(id: String){
        viewModelScope.launch {
            userAuthRepository.deleteRecord(id)
        }
    }
}