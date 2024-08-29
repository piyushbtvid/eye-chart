package com.tv.eyechart.data

import android.util.Log
import com.tv.eyechart.model.DeviceType
import com.tv.eyechart.model.LoginDto
import com.tv.eyechart.model.LoginSuccessDto
import com.tv.eyechart.model.Pin
import com.tv.eyechart.util.Resource
import retrofit2.Response
import javax.inject.Inject

class EyeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getLoginData(deviceType: DeviceType): Resource<LoginDto?> {
        try {
            val response = apiService.getLoginData(deviceType)
            return if (response.isSuccessful) {
                Log.e("MYTAG", "response succes in repo is for login data is $response")
                Resource.Success(response.body())
            } else {
                Log.e("MYTAG", "response error in repo is ${response.message()}")
                Resource.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.e("MYTAG", "response error exception in repo is ${ex.message}")
            return Resource.Error(ex.message ?: "something went wrong")
        }
    }


    suspend fun checkUserLogin(pin: Pin): Resource<LoginSuccessDto?> {
        try {
            val response = apiService.checkUserLogin(pin)
            return if (response.isSuccessful) {
                Log.e("MYTAG", "response succes in repo is for check user $response")
                Resource.Success(response.body())
            } else {
                Log.e("MYTAG", "response error in repo for check login is ${response.message()}")
                Resource.Error(response.message())
            }
        } catch (ex: Exception) {
            Log.e("MYTAG", "response error exception in repo is ${ex.message}")
            return Resource.Error(ex.message ?: "something went wrong")
        }
    }

}