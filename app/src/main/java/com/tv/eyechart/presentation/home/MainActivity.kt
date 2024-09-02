package com.tv.eyechart.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tv.eyechart.presentation.home.adapter.EyeChartAdapter
import com.tv.eyechart.databinding.ActivityMainBinding
import com.tv.eyechart.presentation.setting.SettingActivity

/*
* Main Activity class that loads {@link MainFragment}.
*/class MainActivity : FragmentActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var eyeChartAdapter: EyeChartAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private var youPositionInTheAdapter = 0
    private var currentChartIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        youPositionInTheAdapter = mainViewModel.eyeChartLineItems.size - 1
        setUpEyeChartRvAdapter()
        updateEyeChartItems()
    }

    private fun setUpEyeChartRvAdapter() {
        val manager = LinearLayoutManager(this@MainActivity)
        eyeChartAdapter = EyeChartAdapter()
        mainBinding.eyeChartRv.apply {
            adapter = eyeChartAdapter
            layoutManager = manager
            isNestedScrollingEnabled = true
        }
        mainBinding.eyeChartRv.layoutManager?.scrollToPosition(youPositionInTheAdapter)
    }

    private fun updateEyeChartItems() {
        // Ensure the index is within bounds
        currentChartIndex =
            (currentChartIndex + mainViewModel.allCharts.size) % mainViewModel.allCharts.size

        val currentChart = mainViewModel.allCharts[currentChartIndex]
        eyeChartAdapter.differ.submitList(currentChart)
    }

    private fun increaseDistance() {
        if (youPositionInTheAdapter in 7..11) {
            youPositionInTheAdapter -= 5
            Log.d("MYTAG", "After increase in between 7 to 11 is: $youPositionInTheAdapter")
            mainBinding.eyeChartRv.layoutManager?.scrollToPosition(youPositionInTheAdapter)
            return
        } else if (youPositionInTheAdapter > 0) {
            youPositionInTheAdapter -= 1
            Log.d("MYTAG", "after increase: $youPositionInTheAdapter")
            mainBinding.eyeChartRv.layoutManager?.scrollToPosition(youPositionInTheAdapter)
        }
    }

    private fun decreaseDistance() {
        if (youPositionInTheAdapter == 6) {
            Log.d("MYTAG", "if is called in decrease")
            youPositionInTheAdapter += 5
            mainBinding.eyeChartRv.layoutManager?.scrollToPosition(youPositionInTheAdapter)
            return
        } else if (youPositionInTheAdapter < mainViewModel.eyeChartLineItems.size - 1) {
            Log.d("MYTAG", "else if is called in decrease")
            youPositionInTheAdapter += 1
            Log.d("MYTAG", "after decrease: $youPositionInTheAdapter")
            mainBinding.eyeChartRv.layoutManager?.scrollToPosition(youPositionInTheAdapter)
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                increaseDistance()
                true
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                decreaseDistance()
                true
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                currentChartIndex =
                    (currentChartIndex - 1 + mainViewModel.allCharts.size) % mainViewModel.allCharts.size
                updateEyeChartItems()
                true
            }

            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                currentChartIndex = (currentChartIndex + 1) % mainViewModel.allCharts.size
                updateEyeChartItems()
                true
            }

            KeyEvent.KEYCODE_MENU -> {
                Toast.makeText(this@MainActivity, "setting screen opening!", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
    }

}
