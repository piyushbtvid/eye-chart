package com.tv.eyechart.presentation.authentication

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tv.eyechart.data.EyeRepository
import com.tv.eyechart.data.UserPreferences
import com.tv.eyechart.model.DeviceType
import com.tv.eyechart.model.LoginDto
import com.tv.eyechart.model.LoginSuccessDto
import com.tv.eyechart.model.Pin
import com.tv.eyechart.model.User
import com.tv.eyechart.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: EyeRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _loginData: MutableStateFlow<Resource<LoginDto?>> =
        MutableStateFlow(Resource.Unspecified())
    val loginData = _loginData.asStateFlow()

    private val _userLoginStatus: MutableStateFlow<Resource<LoginSuccessDto?>> =
        MutableStateFlow(Resource.Unspecified())
    val userLoginStatus = _userLoginStatus.asStateFlow()

    private var loginJob: Job? = null

    fun getLoginData(deviceType: DeviceType) {
        loginJob?.cancel()  // Cancel any previous job
        loginJob = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {  // Use isActive to ensure coroutine is not cancelled
                val response = repository.getLoginData(deviceType)
                _loginData.emit(response)
                delay(5 * DateUtils.MINUTE_IN_MILLIS)
            }
        }
    }

    fun checkUserLogin(pin: Pin) {
        loginJob?.cancel()  // Cancel any previous job
        loginJob = viewModelScope.launch(Dispatchers.IO) {
            Log.e("MYTAG","check user login is called in viewmodel method")
            while (isActive) {  // Use isActive to ensure coroutine is not cancelled
                val response = repository.checkUserLogin(pin)
                _userLoginStatus.emit(response)
                delay(5000)
            }
        }
    }

    fun saveUserData(user: User) {
        Log.e("MYTAG", "user that saved in viewModel is $user")
        userPreferences.saveUserName(user.name)
        userPreferences.saveEmail(user.email)
        userPreferences.setLoginStatus(user.isLoggedIn)
    }

    fun getUserData(): Boolean {
        return  userPreferences.isUserLoggedIn()
    }

    override fun onCleared() {
        loginJob?.cancel()  // Ensure the job is cancelled when ViewModel is cleared
        super.onCleared()
    }

}