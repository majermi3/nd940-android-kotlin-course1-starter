package com.udacity.shoestore.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.login.LoginFragmentDirections

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var viewModel: ListViewModel

    var primaryColor: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.listViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        primaryColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary);

        addShoesToList(binding.shoeList)

        binding.addButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }

        return binding.root
    }

    private fun addShoesToList(view: LinearLayout) {
        viewModel.shoes.value?.forEach { shoe ->
            addShoeToList(view, shoe)
        }
    }

    private fun addShoeToList(view: LinearLayout, shoe: Shoe) {
        val header = TextView(context)
        header.text = getString(R.string.company_name_label, shoe.company, shoe.name)
        header.textSize = 24.0F
        header.setTextColor(primaryColor)

        val size = TextView(context)
        size.text = getString(R.string.size_label, shoe.size.toString())
        size.setPadding(16, 4, 0,0)


        val description = TextView(context)
        description.text = shoe.description
        description.setPadding(16, 8,0, 32)

        view.addView(header)
        view.addView(size)
        view.addView(description)
    }
}