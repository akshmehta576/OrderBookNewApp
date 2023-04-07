package com.example.orderbooknewapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderbook")
data class SetUpProfileModel(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val companyName: String,
    val customerName: String,
    val address: String,
    val emailId: String,
    val contactNumber: String,
)