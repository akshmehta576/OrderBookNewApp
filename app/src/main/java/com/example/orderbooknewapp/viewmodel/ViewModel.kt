package com.example.wardrobeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.orderbooknewapp.Repository
import com.example.orderbooknewapp.db.OrderBookDatabase
import com.example.orderbooknewapp.model.Customer
import com.example.orderbooknewapp.model.SetUpProfileModel
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository

    init {
        val dao = OrderBookDatabase.getDatabase(application).getDao()
        repository = Repository(dao)
    }

    fun insertProfileData(orderBookModel: SetUpProfileModel) = viewModelScope.launch { repository.insertProfile(orderBookModel) }

    fun insertCustomerDetails(customer: Customer) = viewModelScope.launch { repository.insertCustomer(customer) }


}