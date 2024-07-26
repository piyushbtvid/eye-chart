package com.tv.eyechart.viewmodel

import androidx.lifecycle.ViewModel
import com.tv.eyechart.R
import com.tv.eyechart.model.EyeChartLineItem

class MainViewModel : ViewModel() {

    val eyeChartLineItems = listOf(
        EyeChartLineItem("T Z O P C", 400),
        EyeChartLineItem("P T O C L", 200),
        EyeChartLineItem("Z L P E D", 100),
        EyeChartLineItem("E T O D C", 70),
        EyeChartLineItem("D P C Z L", 60),
        EyeChartLineItem("C O L T Z", 50),
        EyeChartLineItem("L Z E T P", 40),
        EyeChartLineItem("O D C P T", 30),
        EyeChartLineItem("P Z C D E", 25),
        EyeChartLineItem("T L O P C", 20),
        EyeChartLineItem("Z P E C T", 15),
        EyeChartLineItem("E O D L P", 10)
    )

    private val hotvChartArr = listOf(
        EyeChartLineItem("O H T H T", 400),
        EyeChartLineItem("O H V H V", 200),
        EyeChartLineItem("H O O V V", 100),
        EyeChartLineItem("V T T O T", 70),
        EyeChartLineItem("V H V T V", 60),
        EyeChartLineItem("O O H O T", 50),
        EyeChartLineItem("T O V V T", 40),
        EyeChartLineItem("H O V T O", 30),
        EyeChartLineItem("V H H V O", 25),
        EyeChartLineItem("V O O O V", 20),
        EyeChartLineItem("H V H H T", 15),
        EyeChartLineItem("H H H T T", 10)
    )

    private val tumblingEChartArray = listOf(
        EyeChartLineItem("3 W M E 3", 400),
        EyeChartLineItem("W 3 Ǝ 3 M", 200),
        EyeChartLineItem("Ǝ Ǝ E Ǝ E", 100),
        EyeChartLineItem("3 E W 3 3", 70),
        EyeChartLineItem("M W M W E", 60),
        EyeChartLineItem("E E E W 3", 50),
        EyeChartLineItem("E 3 M M M", 40),
        EyeChartLineItem("3 W Ǝ W Ǝ", 30),
        EyeChartLineItem("Ǝ W Ǝ 3 E", 25),
        EyeChartLineItem("3 W E 3 M", 20),
        EyeChartLineItem("Ǝ M Ǝ W M", 15),
        EyeChartLineItem("M Ǝ Ǝ W 3", 10)
    )


    private val tumblingCChartList = listOf(
        EyeChartLineItem("Ↄ Ↄ Ↄ Ↄ ⊂", 400),
        EyeChartLineItem("Ↄ C Ↄ Ↄ C", 200),
        EyeChartLineItem("Ↄ C Ↄ ⊂ Ↄ", 100),
        EyeChartLineItem("C C ⊂ C ⊂", 70),
        EyeChartLineItem("C C Ↄ C C", 60),
        EyeChartLineItem("Ↄ Ↄ ⊂ ⊂ Ↄ", 50),
        EyeChartLineItem("⊂ Ↄ ⊂ C ⊂", 40),
        EyeChartLineItem("Ↄ C Ↄ Ↄ Ↄ", 30),
        EyeChartLineItem("Ↄ Ↄ Ↄ Ↄ Ↄ", 25),
        EyeChartLineItem("C Ↄ Ↄ Ↄ ⊂", 20),
        EyeChartLineItem("Ↄ Ↄ Ↄ ⊂ Ↄ", 15),
        EyeChartLineItem("⊂ C ⊂ C Ↄ", 10)
    )

    private val iconsCharts = listOf(
        EyeChartLineItem(
            "", 400, drawableList = listOf(
                R.drawable.house,
                R.drawable.bdaycake,
                R.drawable.circle,
                R.drawable.house,
                R.drawable.square
            )
        ), EyeChartLineItem(
            "", 200, drawableList = listOf(
                R.drawable.circle,
                R.drawable.square,
                R.drawable.circle,
                R.drawable.bdaycake,
                R.drawable.house,
            )
        ), EyeChartLineItem(
            "", 100, drawableList = listOf(
                R.drawable.house,
                R.drawable.circle,
                R.drawable.square,
                R.drawable.bdaycake,
                R.drawable.house
            )
        ), EyeChartLineItem(
            "", 70, drawableList = listOf(
                R.drawable.circle,
                R.drawable.circle,
                R.drawable.square,
                R.drawable.bdaycake,
                R.drawable.house,
            )
        ), EyeChartLineItem(
            "", 60, drawableList = listOf(
                R.drawable.square,
                R.drawable.bdaycake,
                R.drawable.square,
                R.drawable.bdaycake,
                R.drawable.house,
            )
        ), EyeChartLineItem(
            "", 50, drawableList = listOf(
                R.drawable.house,
                R.drawable.circle,
                R.drawable.house,
                R.drawable.square,
                R.drawable.bdaycake
            )
        ), EyeChartLineItem(
            "", 40, drawableList = listOf(
                R.drawable.house,
                R.drawable.circle,
                R.drawable.square,
                R.drawable.circle,
                R.drawable.house
            )
        ), EyeChartLineItem(
            "", 30, drawableList = listOf(
                R.drawable.bdaycake,
                R.drawable.circle,
                R.drawable.square,
                R.drawable.house,
                R.drawable.bdaycake
            )
        ), EyeChartLineItem(
            "", 25, drawableList = listOf(
                R.drawable.square,
                R.drawable.square,
                R.drawable.bdaycake,
                R.drawable.circle,
                R.drawable.house
            )
        ), EyeChartLineItem(
            "", 20, drawableList = listOf(
                R.drawable.bdaycake,
                R.drawable.circle,
                R.drawable.house,
                R.drawable.bdaycake,
                R.drawable.circle
            )
        ), EyeChartLineItem(
            "", 15, drawableList = listOf(
                R.drawable.square,
                R.drawable.circle,
                R.drawable.bdaycake,
                R.drawable.circle,
                R.drawable.square
            )
        ), EyeChartLineItem(
            "", 10, drawableList = listOf(
                R.drawable.house,
                R.drawable.bdaycake,
                R.drawable.square,
                R.drawable.circle,
                //incorrect
                R.drawable.circle
            )
        )
    )

    val allCharts = listOf(
        eyeChartLineItems,
        hotvChartArr, tumblingEChartArray, tumblingCChartList, iconsCharts
    )


}