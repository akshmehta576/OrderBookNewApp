package com.example.wardrobeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Index.Order
import com.example.orderbooknewapp.Repository
import com.example.orderbooknewapp.dao.OrderBookDao
import com.example.orderbooknewapp.db.OrderBookDatabase
import com.example.orderbooknewapp.model.SetUpProfileModel
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val allShirts: LiveData<List<SetUpProfileModel>>

    init {
        val dao = OrderBookDatabase.getDatabase(application).getDao()
        repository = Repository(dao)
        allShirts = repository.allShirts
    }

    fun insertShirt(orderBookModel: SetUpProfileModel) =

        viewModelScope.launch { repository.insertShirt(orderBookModel) }



}