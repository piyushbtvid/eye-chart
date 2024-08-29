package com.tv.eyechart.model


import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("deviceType")
    val deviceType: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isLoggedIn")
    val isLoggedIn: Boolean,
    @SerializedName("name")
    val name: String
)