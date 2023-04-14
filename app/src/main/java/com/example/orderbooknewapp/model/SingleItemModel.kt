package com.example.orderbooknewapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SingleItemModel(
    val itemName: String,
    val quantity: String,
    val description: String?,
    val price: Double,
    val totalPrice: Double,
    val taxes: Taxes? = null
): Parcelable

@Parcelize
data class Taxes(
    val sgst: Double? = null,
    val cgst: Double? = null,
    val igst: Double? = null,
): Parcelable