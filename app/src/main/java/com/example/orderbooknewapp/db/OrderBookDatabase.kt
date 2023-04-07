package com.example.orderbooknewapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.orderbooknewapp.dao.OrderBookDao
import com.example.orderbooknewapp.model.SetUpProfileModel

@Database(
    entities = [SetUpProfileModel::class],
    version = 1, exportSchema = false
)

abstract class OrderBookDatabase : RoomDatabase() {
    abstract fun getDao():OrderBookDao
    companion object {
        @Volatile // update every thread
        private var INSTANCE: OrderBookDatabase? = null

        fun getDatabase(context: Context): OrderBookDatabase {

            if (INSTANCE == null) {
                synchronized(this) // for multiple threading
                {
                    INSTANCE =
                        Room.databaseBuilder(context, OrderBookDatabase::class.java, "orderbook_Db")
                            .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }

}