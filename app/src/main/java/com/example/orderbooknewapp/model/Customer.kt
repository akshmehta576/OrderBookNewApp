package com.example.orderbooknewapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Customer(
    val customerName: String,
    val mobileNumber: String,
)

