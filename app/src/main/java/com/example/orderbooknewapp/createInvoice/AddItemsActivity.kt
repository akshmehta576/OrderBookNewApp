package com.example.orderbooknewapp.createInvoice

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.ActivityAddItemsBinding
import com.example.orderbooknewapp.utils.ConvertCurrency
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec


class AddItemsActivity : AppCompatActivity(), SelectTaxInterface, AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityAddItemsBinding
    var totalPrice: String? = null
    var addGst = false
    val sgstOptions = listOf(0.00,2.50,6.00,9.00,14.00)
    val cgstOptions = listOf(0.00,2.50,6.00,9.00,14.00)
    val igstOptions = listOf(0.00,5.00,12.00,18.00,28.00)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.textView.text = "Add Item Details"
        binding.topAppBar.backButtonImageView.setOnClickListener {
            finish()
        }
        changeSubmitButtonColorState()
        setUpTextWatcher()
        binding.aggGstLabel.setOnClickListener {
            if(!addGst){
                binding.taxSection.visibility = View.VISIBLE
            }else{
                binding.taxSection.visibility = View.GONE
            }
            addGst = !addGst
        }

        binding.submitBtn.setOnClickListener {
            if(validateData()){
                changeSubmitButtonColorState()
//                updateData()
            }
        }

        val adSgst = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            sgstOptions
        )
        val adCgst = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            cgstOptions
        )
        val adIgst = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            igstOptions
        )


        adSgst.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        adCgst.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        adIgst.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.sgst.adapter = adSgst
        binding.cgst.adapter = adCgst
        binding.igst.adapter = adIgst

        binding.sgst.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTotalPrice()
            }

        }

        binding.cgst.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTotalPrice()
            }

        }

        binding.igst.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTotalPrice()
            }

        }


        // Create the instance of ArrayAdapter
        // having the list of courses






    }

    fun setUpTextWatcher(){
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeSubmitButtonColorState()
            }

            override fun afterTextChanged(s: Editable?) {
                calculateTotalPrice()
            }
        }
        binding.itemName.addTextChangedListener(textWatcher)
        binding.quantity.addTextChangedListener(textWatcher)
        binding.price.addTextChangedListener(textWatcher)

    }

    private fun calculateTotalPrice() {
        if(!binding.price.text.isNullOrBlank() && !binding.quantity.text.isNullOrBlank() ){
            val totalPriceWithoutGst = binding.price.text.toString().toDouble() * binding.quantity.text.toString().toInt()
            val totalTax = sgstOptions[binding.sgst.selectedItemPosition] + cgstOptions[binding.cgst.selectedItemPosition]+ igstOptions[binding.igst.selectedItemPosition]
            val totalPrice = totalPriceWithoutGst + (totalPriceWithoutGst * totalTax/100)
            binding.totalPrice.text = ConvertCurrency.toLocalCurrency(totalPrice.toLong())
        }

    }

//    private fun updateData() {
//        val item = SingleItemModel(
//            itemName = binding.itemName.text.toString(),
//            quantity = binding.quantity.text.toString(),
//            price = binding.price.text.toString().toDouble(),
//            description = binding.itemDescription.text.toString(),
//        )
//    }

    private fun changeSubmitButtonColorState() {
        if (binding.itemName.text.isNullOrBlank() ||
            binding.quantity.text.isNullOrBlank() ||
            binding.price.text.isNullOrBlank()
        ) {
            binding.submitBtn.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#E4E4E4"));
            binding.submit.setTextColor(Color.parseColor("#33000000"))
        }else{
            binding.submitBtn.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#0077b6"));
            binding.submit.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    fun validateData(): Boolean{
        return !(binding.itemName.text.isNullOrBlank() &&
                binding.quantity.text.isNullOrBlank() &&
                binding.price.text.isNullOrBlank())
    }



    override fun taxSelected(name: String) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}

interface SelectTaxInterface{
    fun taxSelected(name: String)
}
