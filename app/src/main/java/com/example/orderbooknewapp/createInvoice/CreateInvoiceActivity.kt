package com.example.orderbooknewapp.createInvoice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.ActivityCreateInvoiceBinding
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent


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
class CreateInvoiceActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateInvoiceBinding

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
            val dialog = AddCustomerBottomSheetFragment()
            dialog.show(supportFragmentManager, AddCustomerBottomSheetFragment.TAG)
        }

    }
}