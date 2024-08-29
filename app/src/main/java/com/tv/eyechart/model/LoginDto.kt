package com.tv.eyechart.model


import com.google.gson.annotations.SerializedName


data class LoginDto(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
)