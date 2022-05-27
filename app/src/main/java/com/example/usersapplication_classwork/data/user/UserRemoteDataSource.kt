package com.example.usersapplication_classwork.data.user

import com.example.usersapplication_classwork.model.User

class UserRemoteDataSource(val registrationApiService: RegistrationService) {
   suspend fun addUser(user: User): User {
        return registrationApiService.register(user)
    }

    suspend fun getUser(id :String):User? {
       return registrationApiService.getUser(id)
    }
}