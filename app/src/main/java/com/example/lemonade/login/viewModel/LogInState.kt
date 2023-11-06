package com.example.lemonade.login.viewModel

data class LogInState(
    val isSignedIn:Boolean=false,
    val signInError:String? = null
)
