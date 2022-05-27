package com.example.usersapplication_classwork.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersapplication_classwork.data.user.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(val userRepository: UserRepository) : ViewModel() {

    var loginSuccess = MutableLiveData<Boolean>()

    fun login(id: String, password: String) :Boolean{
        var successValue=false
        viewModelScope.launch {
            var user = userRepository.getUser(id)
            if (user != null && user.password == password) {
                loginSuccess.value = true
                successValue=true
            }
        }
        return successValue
    }
}