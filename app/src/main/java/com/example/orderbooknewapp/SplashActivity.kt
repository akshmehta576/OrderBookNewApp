package com.example.orderbooknewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.orderbooknewapp.databinding.ActivitySplashBinding
import com.example.orderbooknewapp.utils.Constants
import com.example.orderbooknewapp.utils.SharedPreferenceManager
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        Handler().postDelayed({

            if(!SharedPreferenceManager.shared.isOnBoardingDone(this)){
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, Constants.splashAnimationDuration)
    }
}