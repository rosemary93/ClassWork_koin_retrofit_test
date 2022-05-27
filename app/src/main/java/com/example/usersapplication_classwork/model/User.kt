package com.example.usersapplication_classwork.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: String="0",
    @Json(name = "name")
    val name: String,
    @Json(name = "password")
    val password: String
)