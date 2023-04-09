package com.example.orderbooknewapp.profile

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.FragmentAddBasicDetailsBinding
import com.example.orderbooknewapp.model.SetUpProfileModel
import com.example.wardrobeapp.viewmodel.ViewModel


class AddBasicDetailsFragment : Fragment() {

    lateinit var binding: FragmentAddBasicDetailsBinding
    lateinit var viewModel: ViewModel
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBasicDetailsBinding.inflate(inflater, container, false)
        binding.topAppBar.textView.text = "Set up Profile"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ViewModel::class.java]
        setUpTextWatcher()
        binding.submitBtn.setOnClickListener {
            if (validateData()) {
                changeSubmitButtonColorState()
                updateData()
                navController.navigate(R.id.action_addBasicDetailsFragment_to_addCompanyIconAndSignatureFragment)
            }
        }
    }

    private fun updateData() {
        viewModel.insertProfileData(
            SetUpProfileModel(
                companyName = binding.companyName.text.toString(),
                ownerName = binding.yourName.text.toString(),
                address = binding.address.text.toString(),
                contactNumber = binding.contactNumber.text.toString(),
                emailId = binding.emailId.text.toString(),
            )
        )
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
        binding.companyName.addTextChangedListener(textWatcher)
        binding.yourName.addTextChangedListener(textWatcher)
        binding.address.addTextChangedListener(textWatcher)
        binding.emailId.addTextChangedListener(textWatcher)
        binding.contactNumber.addTextChangedListener(textWatcher)
    }

    private fun changeSubmitButtonColorState() {
        if (binding.companyName.text.isNullOrBlank() ||
            binding.yourName.text.isNullOrBlank() ||
            binding.address.text.isNullOrBlank() ||
            binding.contactNumber.text.isNullOrBlank() ||
            binding.emailId.text.isNullOrBlank()
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

    fun validateData(): Boolean {
        return !(binding.companyName.text.isNullOrBlank() &&
                binding.yourName.text.isNullOrBlank() &&
                binding.address.text.isNullOrBlank() &&
                binding.contactNumber.text.isNullOrBlank() &&
                binding.emailId.text.isNullOrBlank()
                )
    }

}