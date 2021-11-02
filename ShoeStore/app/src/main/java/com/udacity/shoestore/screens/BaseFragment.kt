package com.udacity.shoestore.screens

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected fun setDisplayHomeAsUpEnabled(showHomeAsUp: Boolean) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }
}