package com.example.orderbooknewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Index.Order
import com.example.orderbooknewapp.createInvoice.CreateInvoiceActivity
import com.example.orderbooknewapp.databinding.ActivityMainBinding
import com.example.orderbooknewapp.db.OrderBookDatabase
import com.example.orderbooknewapp.model.SetUpProfileModel
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent
import com.example.wardrobeapp.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        binding.createInvoiceBtn.setOnClickListener {
            val intent = Intent(this,CreateInvoiceActivity::class.java)
            startActivity(intent)
        }

    }

}