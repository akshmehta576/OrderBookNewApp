package com.example.orderbooknewapp.createInvoice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.ActivityCreateInvoiceBinding
import com.example.orderbooknewapp.model.Customer
import com.example.orderbooknewapp.viewmodel.getShortName
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent
import com.google.gson.Gson
import com.google.gson.GsonBuilder


/**
 * Create Invoice ->
 * 1. Add Customer - Customer Name, Customer Mobile Number(optional)
 * 2. Add Items
 *      2.1 Item Name
 *      2.2 Item Price
 *      2.3 quantity
 *      2.4 Description (optional)
 *      2.5 sgst - 2.5, 6, 9, 14 (optional)
 *      2.6 cgst - 2.5, 6, 9, 14 (optional)
 *      2.7 igst - 5,12,18,28 (optional)
 * 3. Discount - in rs / in %
 * 4. add footer note (optional)
 */
class CreateInvoiceActivity : AppCompatActivity(), SelectCustomerDetails {

    lateinit var binding: ActivityCreateInvoiceBinding
    var customerDetails: Customer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        binding.topAppBar.textView.text = "Create Invoice"
        binding.topAppBar.backButtonImageView.setOnClickListener {
            finish()
        }
        binding.addCustomerSection.newCustomerPlaceholder.setOnClickListener {
            val dialog = AddCustomerBottomSheetFragment(this, customerDetails)
            dialog.show(supportFragmentManager, AddCustomerBottomSheetFragment.TAG)
        }
        binding.addCustomerSection.customerDetails.setOnClickListener {
            if(customerDetails != null){
                val dialog = AddCustomerBottomSheetFragment(this, customerDetails)
                dialog.show(supportFragmentManager, AddCustomerBottomSheetFragment.TAG)
            }
        }
        binding.addItemsSection.newItemPlaceholder.setOnClickListener {
            val intent = Intent(this, AddItemsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun selectCustomer(customer: Customer) {
        customerDetails = customer
        binding.addCustomerSection.customerDetails.visibility = View.VISIBLE
        binding.addCustomerSection.newCustomerPlaceholder.visibility = View.GONE
        binding.addCustomerSection.customerName.text = customer.customerName
        binding.addCustomerSection.customerMobNo.text = customer.mobileNumber
        binding.addCustomerSection.shortNameCustomer.text = getShortName(customer.customerName)
    }
}

object Utils {
    private var gson: Gson? = null
    val gsonParser: Gson?
        get() {
            if (null == gson) {
                val builder = GsonBuilder()
                gson = builder.create()
            }
            return gson
        }
}