package com.tv.eyechart.presentation.authentication

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode
import com.tv.eyechart.R
import com.tv.eyechart.databinding.ActivityLoginBinding
import com.tv.eyechart.model.DeviceType
import com.tv.eyechart.model.Pin
import com.tv.eyechart.presentation.home.MainActivity
import com.tv.eyechart.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException


@AndroidEntryPoint
class LoginActivity : FragmentActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var isCheckingRunning = false
    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    private var loginPin: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        checkUserLoginStatus()
        loginUser()
        observeLoginData()
//        loginPin?.let {
//            Log.e("MYTAG", "Login pin is $it")
//        }
        observeUserLoginStatus()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val url = "www.abc.tvjdchujcjhjcvnncincvkmjndfjuhjbcvjuhjcvjnvc.io.com"
//        generateQRCode(url, binding.barcodeImg)
    }

    private fun loginUser() {

        val isAmazonDevice: Boolean =
            packageManager.hasSystemFeature("amazon.hardware.fire_tv")
        val deviceId: String = if (isAmazonDevice) {
            getUniqueID()
        } else {
            try {
                AdvertisingIdClient.getAdvertisingIdInfo(this@LoginActivity).id ?: getUniqueID()
            } catch (e: IOException) {
                Log.e("Logging", "AdvertisingIdClient:IOException:${e.stackTrace}")
                getUniqueID()
            } catch (e: IllegalStateException) {
                Log.e("Logging", "AdvertisingIdClient:IllegalStateException:${e.stackTrace}")
                getUniqueID()
            } catch (e: GooglePlayServicesNotAvailableException) {
                Log.e(
                    "Logging",
                    "AdvertisingIdClient:GooglePlayServicesNotAvailableException:${e.stackTrace}"
                )
                getUniqueID()
            } catch (e: GooglePlayServicesRepairableException) {
                Log.e(
                    "Logging",
                    "AdvertisingIdClient:GooglePlayServicesRepairableException:${e.stackTrace}"
                )
                getUniqueID()
            }
        }
        val deviceType = DeviceType(deviceType = "fireTv", deviceId = deviceId)
        loginViewModel.getLoginData(deviceType)
    }

    private fun getUniqueID(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID) ?: "123456789"
    }

    private fun observeLoginData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginData.collect { resource ->
                    if (resource is Resource.Success) {
                        val response = resource.data
                        Log.e("MYTAG", " response in main is for login data $response")
                        val pin = response?.data?.pin
                        binding.pinTxt1.text = pin?.get(0).toString()
                        binding.pinTxt2.text = pin?.get(1).toString()
                        binding.pinTxt3.text = pin?.get(2).toString()
                        binding.pinTxt4.text = pin?.get(3).toString()
                        binding.pinTxt5.text = pin?.get(4).toString()
                        binding.pinTxt6.text = pin?.get(5).toString()
                        binding.urlTxt.text = response?.data?.url
                        Glide.with(this@LoginActivity)
                            .load(response?.data?.qrCode)
                            .error(R.color.black)
                            .into(binding.barcodeImg)

                        loginPin = response?.data?.pin
                        if (!isCheckingRunning) {
                            loginPin?.let { Pin(pin = it) }
                                ?.let { loginViewModel.checkUserLogin(it) }
                            isCheckingRunning = true
                        }
                    }
                }
            }
        }
    }


    private fun observeUserLoginStatus() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.userLoginStatus.collect { resource ->
                    if (resource is Resource.Success) {
                        if (resource.data?.user?.isNotEmpty() == true) {
                            val user = resource.data.user[0]
                            Log.e("MYTAG", "user login status in user is  in main is $user")
                            loginViewModel.saveUserData(user)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun checkUserLoginStatus() {
        val isLogin = loginViewModel.getUserData()
        if (isLogin) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun generateQRCode(url: String, barcodeImageView: ImageView) {
        val myBitmap: Bitmap = net.glxn.qrgen.android.QRCode.from(url)
            .withSize(binding.barcodeImg.width + 1000, binding.barcodeImg.height + 1000)
            .bitmap()
        barcodeImageView.setImageBitmap(myBitmap)
    }
}