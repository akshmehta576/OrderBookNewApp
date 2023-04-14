package com.example.orderbooknewapp.createInvoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orderbooknewapp.databinding.AllItemsListBinding
import com.example.orderbooknewapp.databinding.SingleItemBinding
import com.example.orderbooknewapp.model.SingleItemModel
import com.example.orderbooknewapp.utils.ConvertCurrency
import kotlin.math.roundToLong

class ShowAllItemsAdapter(
    var items: List<SingleItemModel>
): RecyclerView.Adapter<ShowAllItemsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: AllItemsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AllItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemName.text = item.itemName
        holder.binding.price.text = ConvertCurrency.toLocalCurrency(item.totalPrice)
        holder.binding.quantity.text = "${item.quantity}"
        holder.binding.sno.text = "${position+1}."

    }

}