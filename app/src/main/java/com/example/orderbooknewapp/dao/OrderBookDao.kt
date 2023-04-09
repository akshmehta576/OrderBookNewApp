package com.example.orderbooknewapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.orderbooknewapp.model.Customer
import com.example.orderbooknewapp.model.SetUpProfileModel

@Dao
interface OrderBookDao {
    @Insert
    suspend fun setUpProfile(orderBookModel: SetUpProfileModel)

    @Insert
    suspend fun addCustomer(customer: Customer)

//    @Query("SELECT * FROM customers_orderbook where customerId == :customerId")
//    fun getCustomer(customerId: Int): LiveData<List<SetUpProfileModel>>

}