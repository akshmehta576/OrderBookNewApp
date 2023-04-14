package com.example.orderbooknewapp.createInvoice

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.orderbooknewapp.databinding.ActivityAddItemsBinding
import com.example.orderbooknewapp.model.SingleItemModel
import com.example.orderbooknewapp.model.Taxes
import com.example.orderbooknewapp.utils.ConvertCurrency
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent


class AddItemsActivity : AppCompatActivity(), SelectTaxInterface, AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityAddItemsBinding
    var totalPrice: String? = null
    var addGst = false
    val sgstOptions = listOf(0.00,2.50,6.00,9.00,14.00)
    val cgstOptions = listOf(0.00,2.50,6.00,9.00,14.00)
    val igstOptions = listOf(0.00,5.00,12.00,18.00,28.00)
    var totalItemPrice: Long= 0
    var itemDetails: SingleItemModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemDetails = intent.getParcelableExtra<SingleItemModel>("update_item")
        binding.topAppBar.textView.text = "Add Item Details"
        binding.topAppBar.backButtonImageView.setOnClickListener {
            finish()
        }
        changeSubmitButtonColorState()
        setUpTextWatcher()
        updateItem(itemDetails)
        binding.aggGstLabel.setOnClickListener {
            binding.price.hideKeyboard()
            binding.itemName.hideKeyboard()
            binding.quantitySection.hideKeyboard()
            binding.quantity.hideKeyboard()
            if(!addGst){
                binding.aggGstLabel.text = "- REMOVE TAXES"
                binding.aggGstLabel.setTextColor(Color.parseColor("#9F1414"))
                binding.taxSection.visibility = View.VISIBLE
            }else{
                binding.sgst.post(Runnable { binding.sgst.setSelection(0) })
                binding.cgst.post(Runnable { binding.cgst.setSelection(0) })
                binding.igst.post(Runnable { binding.igst.setSelection(0) })
                calculateTotalPrice()
                binding.aggGstLabel.text = "+ ADD GST & TAX DETAILS"
                binding.aggGstLabel.setTextColor(Color.parseColor("#0077b6"))
                binding.taxSection.visibility = View.GONE
            }
            addGst = !addGst
        }

        binding.submitBtn.setOnClickListener {
            if(validateData()){
                changeSubmitButtonColorState()
                val resultIntent = Intent()
                val data  =  SingleItemModel(
                    itemName = binding.itemName.text.toString(),
                    description = binding.itemDescription.text.toString(),
                    quantity = binding.quantity.text.toString(),
                    price = binding.price.text.toString().toDouble(),
                    totalPrice = totalItemPrice,
                    taxes = Taxes(
                        sgst = sgstOptions[binding.sgst.selectedItemPosition],
                        cgst = cgstOptions[binding.cgst.selectedItemPosition],
                        igst = igstOptions[binding.igst.selectedItemPosition],
                    )
                )
                resultIntent.putExtra("item_detail", data)
                setResult(RESULT_OK, resultIntent)
                finish()
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
            var totalTax: Double = 0.0
            totalTax = if(itemDetails == null){
                sgstOptions[binding.sgst.selectedItemPosition] + cgstOptions[binding.cgst.selectedItemPosition]+ igstOptions[binding.igst.selectedItemPosition]
            }else{
                (itemDetails?.taxes?.sgst ?: 0.0) +  (itemDetails?.taxes?.cgst ?: 0.0) + ( itemDetails?.taxes?.igst ?: 0.0)
            }
            totalItemPrice = (totalPriceWithoutGst + (totalPriceWithoutGst * totalTax/100)).toLong()
            binding.totalPrice.text = ConvertCurrency.toLocalCurrency(totalItemPrice)
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
    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun updateItem(item: SingleItemModel?) {
        if(item != null){
            if(item.taxes?.sgst == 0.0 && item.taxes.cgst == 0.0 && item.taxes.igst == 0.0){
                binding.aggGstLabel.text = "+ ADD GST & TAX DETAILS"
                binding.aggGstLabel.setTextColor(Color.parseColor("#0077b6"))
                binding.taxSection.visibility = View.GONE
            }else{
                binding.aggGstLabel.text = "- REMOVE TAXES"
                binding.aggGstLabel.setTextColor(Color.parseColor("#9F1414"))
                binding.taxSection.visibility = View.VISIBLE
                sgstOptions.forEachIndexed { position, it ->
                    if(it == item.taxes?.sgst){
                        Log.i("taxes",sgstOptions[position].toString())

                        binding.sgst.post(Runnable { binding.sgst.setSelection(position,true) })
                    }
                }
                cgstOptions.forEachIndexed { position, it ->
                    if(it == item.taxes?.cgst){
                        Log.i("taxes",cgstOptions[position].toString())
                        binding.cgst.post(Runnable { binding.cgst.setSelection(position,true) })
                    }
                }
                igstOptions.forEachIndexed { position, it ->
                    if(it == item.taxes?.igst){
                        Log.i("taxes",igstOptions[position].toString())
                        binding.igst.post(Runnable { binding.igst.setSelection(position,true) })
                    }
                }

            }
            binding.itemName.setText(item.itemName)
            binding.quantity.setText(item.quantity)
            binding.price.setText("${item.price}")
            binding.itemDescription.setText(item.description)
            binding.totalPrice.text = "${item?.totalPrice}"
        }
    }

}

interface SelectTaxInterface{
    fun taxSelected(name: String)
}
