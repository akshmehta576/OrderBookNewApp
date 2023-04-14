package com.example.orderbooknewapp.utils

import java.text.DecimalFormat

class ConvertCurrency {
    companion object{
        fun toLocalCurrency(amount: Long?): String{
//            val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
            return DecimalFormat("₹ ##,##,##,###").format(amount);
        }
    }
}