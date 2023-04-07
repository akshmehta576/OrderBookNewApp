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
    private lateinit var orderBookDatabase: OrderBookDatabase
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ViewModel::class.java]

        orderBookDatabase = OrderBookDatabase.getDatabase(this)
        addData()
    }

    private fun addData() {
        viewModel.insertShirt(
            SetUpProfileModel(
                1,
                "TATA",
                "Aksh",
                "13,Navlok",
                "aksh@gmai;.com",
                "+91798765432"
            )
        )
    }
}