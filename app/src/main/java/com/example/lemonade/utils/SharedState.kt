package com.example.lemonade.utils

import android.content.Context

class SharedState() {
    fun isFirstTime(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(FIRST_TIME_KEY, true)
    }

    fun setFirstTime(context: Context, isFirstTime: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_TIME_KEY, isFirstTime)
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "MyPrefsFile"
        private const val FIRST_TIME_KEY = "isFirstTime"
    }
}