package com.udacity.shoestore.screens

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected fun setDisplayHomeAsUpEnabled(showHomeAsUp: Boolean) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    protected fun showLongToast(stringId: Int) {
        Toast.makeText(context, getString(stringId), Toast.LENGTH_LONG).show()
    }
}