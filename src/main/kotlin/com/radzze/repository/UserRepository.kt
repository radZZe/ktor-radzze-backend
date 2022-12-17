package com.radzze.repository

import com.radzze.service.CreateUserParams
import com.radzze.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params:CreateUserParams)  :BaseResponse<Any>
    suspend fun loginUser(email:String,password:String):BaseResponse<Any>
}