package com.example.orderbooknewapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.orderbooknewapp.model.SetUpProfileModel

@Dao
interface OrderBookDao {
    @Insert
    suspend fun upsertpant(orderBookModel: SetUpProfileModel)

    @Query("SELECT * FROM orderbook")
    fun getAllPants(): LiveData<List<SetUpProfileModel>>

}