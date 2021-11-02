package com.udacity.shoestore.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.screens.BaseFragment

class LoginFragment : BaseFragment() {

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

        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
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
                saveLoginState()
                navigateToWelcome()
            }
        }
        binding.signUpButton.setOnClickListener {
            hideKeyboard(it)
            if (!showErrorMessage()) {
                saveLoginState()
                navigateToWelcome()
            }
        }

        return binding.root
    }

    private fun saveLoginState() {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.saved_login_state), true)
            apply()
        }
    }

    private fun navigateToWelcome() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

    private fun showErrorMessage(): Boolean {
        if (!loginTabActive && !viewModel.isNameValid()) {
            showLongToast(R.string.invalid_name)
        } else if (!viewModel.isEmailValid()) {
            showLongToast(R.string.invalid_email)
        } else if (!viewModel.isPasswordValid()) {
            showLongToast(R.string.invalid_password)
        } else if (!loginTabActive && !viewModel.isPasswordRepeatValid()) {
            showLongToast(R.string.password_not_same)
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