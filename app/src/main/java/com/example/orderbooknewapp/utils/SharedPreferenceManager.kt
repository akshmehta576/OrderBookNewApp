package com.example.orderbooknewapp.utils

import android.content.Context

class SharedPreferenceManager() {

    companion object {
        val shared: SharedPreferenceManager = SharedPreferenceManager()
    }
    fun isOnBoardingDone(context: Context) : Boolean {
        val sharedPreference =  context.getSharedPreferences(Constants.sharedPreferenceFileName,
            Context.MODE_PRIVATE)
        return sharedPreference.getBoolean(Constants.isOnboradingDone,false)
    }

    fun updateOnBoarding(context: Context, value: Boolean) {
        val sharedPreference =  context. getSharedPreferences(Constants.sharedPreferenceFileName,
            Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean(Constants.isOnboradingDone,value)
        editor.commit()
    }
}