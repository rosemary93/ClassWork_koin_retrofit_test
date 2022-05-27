package com.example.usersapplication_classwork.data.user

import com.example.usersapplication_classwork.model.User

class UserRepository(
    val userLocalDataSource: UserLocalDataSource,
   val  userRemoteDataSource: UserRemoteDataSource
) {
    var user:User?=null

    suspend fun addUser(user: User): User {
        this.user=userRemoteDataSource.addUser(user)
        return this.user!!
    }

    suspend fun getUser(id :String):User?{
       return userRemoteDataSource.getUser(id)
    }

}