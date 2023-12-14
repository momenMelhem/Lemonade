package com.example.lemonade.utils.auth

data class SignInResult(
    val data :UserData?,
    val errorMessage : String?
)
data class UserData(
        val userId:String?,
        val userName:String?,
        val email:String?,
        val password:String?
 )
