package com.tv.eyechart.model


import com.google.gson.annotations.SerializedName


data class LoginSuccessDto(
    @SerializedName("message")
    val message: String,
    @SerializedName("user")
    val user: List<User>
)