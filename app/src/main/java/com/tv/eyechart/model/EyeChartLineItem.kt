package com.tv.eyechart.model

data class EyeChartLineItem(
    var text: String,
    var textSize: Int,
    var drawableList : List<Int> = emptyList()
)
