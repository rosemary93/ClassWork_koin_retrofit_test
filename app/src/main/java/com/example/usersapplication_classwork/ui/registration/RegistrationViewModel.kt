package com.example.usersapplication_classwork.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersapplication_classwork.data.user.UserRepository
import com.example.usersapplication_classwork.model.User
import kotlinx.coroutines.launch

class RegistrationViewModel( val userRepository: UserRepository):ViewModel() {

    var userLiveData=MutableLiveData<User>()
    fun register(user: User) {
        viewModelScope.launch {
            try {
                val userWithID=userRepository.addUser(user)
                userLiveData.value=userWithID
            }catch (e:Exception){

            }

        }
    }
}