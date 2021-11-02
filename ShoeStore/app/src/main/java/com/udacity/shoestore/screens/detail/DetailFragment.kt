package com.udacity.shoestore.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.screens.BaseFragment

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.addButton.setOnClickListener {
            if (validateForm()) {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToListFragment(viewModel.shoe.value),
                )
            }
        }

        return binding.root
    }

    private fun validateForm(): Boolean {
        if (!viewModel.shoe.value!!.hasName()) {
            showErrorToast(R.string.invalid_name)
        } else if (!viewModel.shoe.value!!.hasCompany()) {
            showErrorToast(R.string.invalid_company)
        } else {
            return true
        }
        return false
    }

    private fun showErrorToast(stringId: Int) {
        Toast.makeText(context, getString(stringId), Toast.LENGTH_LONG).show()
    }
}