package com.example.orderbooknewapp.createInvoice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.ActivityListOfItemsBinding
import com.example.orderbooknewapp.model.SingleItemModel

class ListOfItemsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListOfItemsBinding
    lateinit var adapter: AllSelectedItemsAdapter
    lateinit var listOfItems: java.util.ArrayList<SingleItemModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = AllSelectedItemsAdapter(listOfItems)
        binding.recyclerView.adapter = adapter

        binding.addMoreItemBtn.setOnClickListener {
            val intent = Intent(this, AddItemsActivity::class.java)
            startActivityForResult(intent, 10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10  && resultCode  == RESULT_OK) {
            val itemDetails= data?.getParcelableExtra<SingleItemModel>("item_detail")
            if (itemDetails != null) {
                listOfItems.add(itemDetails)
                adapter.items = listOfItems
                adapter.notifyDataSetChanged()
            }
        }
    }


}