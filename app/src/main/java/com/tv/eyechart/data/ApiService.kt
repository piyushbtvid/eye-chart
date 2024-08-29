package com.tv.eyechart.data

import com.tv.eyechart.model.DeviceType
import com.tv.eyechart.model.LoginDto
import com.tv.eyechart.model.LoginSuccessDto
import com.tv.eyechart.model.Pin
import com.tv.eyechart.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {


    @POST(Constants.LOGIN_END_POINT)
    suspend fun getLoginData(
        @Body deviceType: DeviceType
    ): Response<LoginDto>

    @POST(Constants.CHECK_LOGIN_POINT)
    suspend fun checkUserLogin(
        @Body pin: Pin
    ): Response<LoginSuccessDto>

}