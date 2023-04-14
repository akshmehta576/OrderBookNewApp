package com.example.orderbooknewapp.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat

class ConvertCurrency {
    companion object{
        fun toLocalCurrency(amount: Long?): String{
//            val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
            return DecimalFormat("₹ ##,##,##,###").format(amount);
        }


    }
}