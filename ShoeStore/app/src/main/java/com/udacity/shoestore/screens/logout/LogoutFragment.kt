package com.udacity.shoestore.screens.logout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R

class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logout()
        findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentToLoginFragment())

        return null
    }

    private fun logout() {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putBoolean(getString(R.string.saved_login_state), false)
            apply()
        }
    }
}