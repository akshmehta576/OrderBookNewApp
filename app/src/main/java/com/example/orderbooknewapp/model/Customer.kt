package com.example.orderbooknewapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers_orderbook")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val customerName: String,
    val mobileNumber: String,
)
