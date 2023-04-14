package com.example.orderbooknewapp.createInvoice

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.example.orderbooknewapp.databinding.FragmentAddCustomerBottomSheetBinding
import com.example.orderbooknewapp.model.Customer
import com.example.wardrobeapp.viewmodel.ViewModel

class AddCustomerBottomSheetFragment(val listener: SelectCustomerDetails,val customerDetails: Customer?) : SuperBottomSheetFragment() {

    lateinit var binding: FragmentAddCustomerBottomSheetBinding
    lateinit var viewModel: ViewModel

    companion object{
        const val TAG = "AddCustomerBottomSheetFragment"
    }

    override fun getExpandedHeight() = ViewGroup.LayoutParams.WRAP_CONTENT
    override fun isSheetAlwaysExpanded() = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        binding =  FragmentAddCustomerBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ViewModel::class.java]
        binding.backBtn.setOnClickListener {
            dialog?.dismiss()
        }
        updateData()

        setUpTextWatcher()
        changeSubmitButtonColorState()

        binding.submitBtn.setOnClickListener {
            if(validateData()){
                changeSubmitButtonColorState()
                listener.selectCustomer(
                    Customer(
                    customerName = binding.customerName.text.toString(),
                    mobileNumber = binding.mobileNumber.text.toString()
                )
                )
                dialog?.dismiss()
            }
        }

    }

    private fun updateData() {
        if(customerDetails != null){
            binding.customerName.setText(customerDetails!!.customerName)
            binding.mobileNumber.setText(customerDetails!!.mobileNumber)
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

            }
        }
        binding.customerName.addTextChangedListener(textWatcher)
        binding.mobileNumber.addTextChangedListener(textWatcher)
    }

    private fun changeSubmitButtonColorState() {
        if (binding.customerName.text.isNullOrBlank() ||
            binding.mobileNumber.text.isNullOrBlank()
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


    private fun validateData(): Boolean {
        return !(binding.mobileNumber.text.isNullOrBlank() || binding.customerName.text.isNullOrBlank())
    }

}

interface SelectCustomerDetails{
    fun selectCustomer(customer: Customer)
}
