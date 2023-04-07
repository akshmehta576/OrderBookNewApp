package com.example.orderbooknewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.orderbooknewapp.databinding.ActivityProfileBinding
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()

    }
}