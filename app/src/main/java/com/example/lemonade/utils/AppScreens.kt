package com.example.lemonade.utils

const val FILTER_ARGUMENT_KEY = "filterState"
const val ITEMS_ARGUMENT_KEY = "items"
const val Cart_ARGUMENT_KEY = "cart"
sealed class AppScreens(val rout:String){

     object Login : AppScreens(
        rout = "Login",

    )
     object Register : AppScreens(
        rout = "Register"

    )
     object Main : AppScreens(
        rout = "Main"

    )
    object Splash : AppScreens(
        rout = "Splash"

    )
    object FilteredShops : AppScreens(

        rout = "Filtered/{$FILTER_ARGUMENT_KEY}"
    )
    object Items : AppScreens(

        rout = "Items/{$ITEMS_ARGUMENT_KEY}"
    )
    object Cart : AppScreens(

        rout = "Cart/{$Cart_ARGUMENT_KEY}"
    )
}
