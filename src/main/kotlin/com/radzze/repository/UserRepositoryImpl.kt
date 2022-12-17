package com.radzze.repository

import com.radzze.service.CreateUserParams
import com.radzze.service.UserService
import com.radzze.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
       return  if(isEmailExist(params.email)){
            BaseResponse.ErrorResponse(message = "Email already exist")
        }else{
            val user = userService.registerUser(params)
            if(user != null){
                //TODO generate token ofr user
                BaseResponse.SuccessResponse(data = user)
            }else{
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email:String):Boolean{
        return userService.findUserByEmail(email) != null
    }
}