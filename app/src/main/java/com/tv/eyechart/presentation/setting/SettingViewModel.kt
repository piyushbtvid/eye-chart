package com.tv.eyechart.presentation.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tv.eyechart.data.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val userPreferences: UserPreferences) :
    ViewModel() {


    fun changeUserLoginStatus(isLogin: Boolean) {
        Log.e("MYTAG", "change user login status called in viewmodel with $isLogin")
        userPreferences.setLoginStatus(isLogin)
    }

}