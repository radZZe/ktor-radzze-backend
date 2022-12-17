package com.radzze.service

import com.radzze.models.User

interface UserService {

    suspend fun registerUser(params:CreateUserParams): User?

    suspend fun findUserByEmail(email:String):User?
}