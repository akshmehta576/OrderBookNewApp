package com.example.orderbooknewapp

import androidx.lifecycle.LiveData
import com.example.orderbooknewapp.dao.OrderBookDao
import com.example.orderbooknewapp.model.SetUpProfileModel

class Repository(private val orderBookDao: OrderBookDao) {

    val allShirts: LiveData<List<SetUpProfileModel>> = orderBookDao.getAllPants()

    suspend fun insertShirt(orderBookModel: SetUpProfileModel) {
        orderBookDao.upsertpant(orderBookModel)
    }




}