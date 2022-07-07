package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.ui.AppViewModel
import com.udacity.shoestore.ui.MainActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
     val viewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        initOnClickListeners()
        return binding.root
    }

    /*
    This Function should be replaced with two functions that handle login and sign up
    functionality through callbacks provided by view model.
    This Code is just simulating the login or signup process.
     */
    private fun moveToWelcomeScreen() {
        val error: String? = viewModel.validate(binding)
        if (error == null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        } else {
            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initOnClickListeners() {
        binding.loginButton.setOnClickListener {
            moveToWelcomeScreen()
        }
        binding.createAccountText.setOnClickListener {
            moveToWelcomeScreen()
        }
    }
}