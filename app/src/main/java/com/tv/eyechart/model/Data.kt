package com.tv.eyechart.model


import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("pin")
    val pin: String,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("url")
    val url: String
)