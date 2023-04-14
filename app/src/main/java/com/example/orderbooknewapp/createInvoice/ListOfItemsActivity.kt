package com.example.orderbooknewapp.createInvoice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.ActivityListOfItemsBinding
import com.example.orderbooknewapp.model.SingleItemModel
import com.example.orderbooknewapp.model.Taxes
import com.example.orderbooknewapp.utils.ConvertCurrency
import com.example.orderbooknewapp.viewmodel.makeStatusBarTransparent

class ListOfItemsActivity : AppCompatActivity(), SelectItemInterface {

    lateinit var binding: ActivityListOfItemsBinding
    lateinit var adapter: AllSelectedItemsAdapter
    var listOfItems: java.util.ArrayList<SingleItemModel> = arrayListOf()
    var totalPrice: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeStatusBarTransparent()
        binding.topAppBar.textView.text = "Add Items"
        binding.topAppBar.backButtonImageView.setOnClickListener {
            finish()
        }
        listOfItems = intent.getParcelableArrayListExtra("all_items") ?: arrayListOf()
        adapter = AllSelectedItemsAdapter(listOfItems, this)
        binding.recyclerView.adapter = adapter

        binding.addMoreItemBtn.setOnClickListener {
            val intent = Intent(this, AddItemsActivity::class.java)
            startActivityForResult(intent, 10)
        }
        binding.submitBtn.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("all_items", listOfItems)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10  && resultCode  == RESULT_OK) {
            val itemDetails= data?.getParcelableExtra<SingleItemModel>("item_detail")
            listOfItems.clear()
            if (itemDetails != null) {
                totalPrice+=itemDetails.totalPrice
                binding.totalPriceTv.text = ConvertCurrency.toLocalCurrency(totalPrice)
                listOfItems.add(itemDetails)
                adapter.items = listOfItems
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun updateItem(item: SingleItemModel) {
        val intent = Intent(this, AddItemsActivity::class.java)
        intent.putExtra("update_item",item)
        startActivityForResult(intent, 10)
    }
}

interface SelectItemInterface{
    fun updateItem(item: SingleItemModel)

}