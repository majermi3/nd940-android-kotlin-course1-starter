package com.udacity.shoestore.screens.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    private var loginTabActive = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    binding.loginLayout.visibility = View.VISIBLE
                    binding.signUpLayout.visibility = View.GONE
                    loginTabActive = true
                } else {
                    binding.loginLayout.visibility = View.GONE
                    binding.signUpLayout.visibility = View.VISIBLE
                    loginTabActive = false
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        binding.loginButton.setOnClickListener {
            hideKeyboard(it)
            if (!showErrorMessage()) {
                navigateToWelcome()
            }
        }
        binding.signUpButton.setOnClickListener {
            hideKeyboard(it)
            if (!showErrorMessage()) {
                navigateToWelcome()
            }
        }

        return binding.root
    }

    private fun navigateToWelcome() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

    private fun showErrorMessage(): Boolean {
        if (!loginTabActive && !viewModel.isNameValid()) {
            Toast.makeText(context, getString(R.string.invalid_name), Toast.LENGTH_LONG).show()
        } else if (!viewModel.isEmailValid()) {
            Toast.makeText(context, getString(R.string.invalid_email), Toast.LENGTH_LONG).show()
        } else if (!viewModel.isPasswordValid()) {
            Toast.makeText(context, getString(R.string.invalid_password), Toast.LENGTH_LONG).show()
        } else if (!loginTabActive && !viewModel.isPasswordRepeatValid()) {
            Toast.makeText(context, getString(R.string.password_not_same), Toast.LENGTH_LONG).show()
        } else {
            return false
        }
        return true
    }

    private fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}