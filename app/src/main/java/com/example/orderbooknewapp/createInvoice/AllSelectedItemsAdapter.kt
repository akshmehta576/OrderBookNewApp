package com.example.orderbooknewapp.createInvoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orderbooknewapp.databinding.SingleItemBinding
import com.example.orderbooknewapp.model.SingleItemModel
import com.example.orderbooknewapp.utils.ConvertCurrency
import kotlin.math.roundToLong

class AllSelectedItemsAdapter(
    var items: List<SingleItemModel>
): RecyclerView.Adapter<AllSelectedItemsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemName.text = item.itemName
        holder.binding.price.text = ConvertCurrency.toLocalCurrency(item.totalPrice.roundToLong())
        holder.binding.quantity.text = "${item.quantity} x ${ConvertCurrency.toLocalCurrency(item.price.roundToLong())}"
        if(item.taxes != null){
            holder.binding.otherTaxesSection.visibility = View.VISIBLE
            if(item.taxes.sgst != null){
                holder.binding.sgstSection.visibility = View.VISIBLE
                holder.binding.sgst.text = "${item.taxes.sgst}"
            }else{
                holder.binding.sgstSection.visibility = View.GONE
            }

            if(item.taxes.cgst != null){
                holder.binding.cgstSection.visibility = View.VISIBLE
                holder.binding.cgst.text = "${item.taxes.cgst}"
            }else{
                holder.binding.cgstSection.visibility = View.GONE
            }

            if(item.taxes.igst != null){
                holder.binding.igstSection.visibility = View.VISIBLE
                holder.binding.igst.text = "${item.taxes.igst}"
            }else{
                holder.binding.igstSection.visibility = View.GONE
            }

        }else{
            holder.binding.otherTaxesSection.visibility = View.GONE
        }
    }

}