package com.example.lemonade.utils


sealed class AppScreens(val rout:String){

     object Login : AppScreens(
        rout = "Login",

    )
     object Register : AppScreens(
        rout = "Register"

    )

     object HavAccount : AppScreens(
       rout = "Have Account"

    )
     object Main : AppScreens(
        rout = "Main"

    )
}
