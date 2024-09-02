package com.tv.eyechart.data

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_IS_LOGIN = "is_login"
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun saveEmail(email: String) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    fun setLoginStatus(isLogin: Boolean) {
        Log.e("MYTAG", "change user login status called in user perfclass with $isLogin")
        sharedPreferences.edit().putBoolean(KEY_IS_LOGIN, isLogin).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGIN, false)
    }
}