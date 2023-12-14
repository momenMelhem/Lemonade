package com.example.lemonade.utils

import android.content.Context
import android.content.SharedPreferences

class SharedState {



    fun userName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_NAME_KEY, "")
    }

    fun setUserName(context: Context, userName: String) {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_NAME_KEY, userName)
        editor.apply()
    }
    fun setUserID(context: Context, userID: String) {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID_KEY, userID)
        editor.apply()
    }
    fun userID(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_ID_KEY,"")
    }
    fun email(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getString(EMAIL_KEY,"")
    }

    fun setEmail(context: Context, email: String) {
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(EMAIL_KEY, email)
        editor.apply()
    }
    fun  removeFromSharedPref(key:String,context: Context){
        val sharedPreferences = context.getSharedPreferences(FIRE_BASE_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }




    companion object {
        private const val PREFS_NAME = "MyPrefsFile"
        private const val FIRE_BASE_PREF= "fireBasePref"
        private const val FIRST_TIME_KEY = "isFirstTime"
        const val USER_NAME_KEY = "userName"
        const val USER_ID_KEY = "userId"


        const val EMAIL_KEY = "isFirstTime"
    }
}