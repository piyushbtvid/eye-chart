package com.tv.eyechart.presentation.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.tv.eyechart.R
import com.tv.eyechart.databinding.ActivitySettingBinding
import com.tv.eyechart.presentation.authentication.LoginActivity
import com.tv.eyechart.presentation.authentication.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : FragmentActivity() {
    private val binding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    private val settingViewModel by lazy {
        ViewModelProvider(this)[SettingViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.signOutBtnId.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.signOutBtnId.setBackgroundResource(R.color.black)
            } else {
                binding.signOutBtnId.setBackgroundResource(R.color.default_background)
            }
        }
        binding.signOutBtnId.requestFocus()
        binding.signOutBtnId.setOnClickListener {
            Log.e("MYTAG", "sign out click called")
            settingViewModel.changeUserLoginStatus(isLogin = false)
            Toast.makeText(this@SettingActivity, "User Sign out Success!", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this@SettingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}