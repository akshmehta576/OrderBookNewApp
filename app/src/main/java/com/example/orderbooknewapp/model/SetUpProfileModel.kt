package com.example.orderbooknewapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_profile_orderbook")
data class SetUpProfileModel(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val companyName: String,
    val ownerName: String,
    val address: String,
    val emailId: String,
    val contactNumber: String,
)