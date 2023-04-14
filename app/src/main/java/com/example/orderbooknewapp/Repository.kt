package com.example.orderbooknewapp

import androidx.lifecycle.LiveData
import com.example.orderbooknewapp.dao.OrderBookDao
import com.example.orderbooknewapp.model.Customer
import com.example.orderbooknewapp.model.SetUpProfileModel

class Repository(private val orderBookDao: OrderBookDao) {


    suspend fun insertProfile(orderBookModel: SetUpProfileModel) {
        orderBookDao.setUpProfile(orderBookModel)
    }


//    suspend fun getCustomer(customerId: Int) {
//        orderBookDao.getCustomer(customerId)
//    }




}