package com.example.orderbooknewapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderbooknewapp.MainActivity
import com.example.orderbooknewapp.R
import com.example.orderbooknewapp.databinding.FragmentAddCompanyIconAndSignatureBinding


class AddCompanyIconAndSignatureFragment : Fragment() {

    lateinit var binding: FragmentAddCompanyIconAndSignatureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddCompanyIconAndSignatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.textView.text = "Set up Profile"
        binding.submitBtn.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
    }


}