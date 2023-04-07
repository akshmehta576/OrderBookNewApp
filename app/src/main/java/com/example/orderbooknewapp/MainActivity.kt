package com.example.orderbooknewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Index.Order
import com.example.orderbooknewapp.databinding.ActivityMainBinding
import com.example.orderbooknewapp.db.OrderBookDatabase
import com.example.orderbooknewapp.model.SetUpProfileModel
import com.example.wardrobeapp.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}