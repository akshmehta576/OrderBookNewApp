package com.example.orderbooknewapp.createInvoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orderbooknewapp.databinding.ItemSelectTaxBinding


class ChooseTaxesAdapter(
    private val getCategoryNamesList: List<String>,
    private val listener: SelectTaxInterface,
): RecyclerView.Adapter<ChooseTaxesAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemSelectTaxBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSelectTaxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return getCategoryNamesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sponsorName = getCategoryNamesList[position]
        if(position == getCategoryNamesList.size-1){
            holder.binding.div1.visibility = View.GONE
        }else{
            holder.binding.div1.visibility = View.VISIBLE
        }
        holder.binding.categoryName.text = sponsorName
        holder.binding.parentLayout.setOnClickListener {
            listener.taxSelected(sponsorName)
        }

    }
}
