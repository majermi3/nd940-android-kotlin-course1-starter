package com.udacity.shoestore.screens.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.BaseFragment

class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var viewModel: ListViewModel

    var primaryColor: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!isLoggedIn()) {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(false)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        if (arguments != null) {
            val args = ListFragmentArgs.fromBundle(requireArguments())
            if (args.shoe != null) {
                viewModel.addShoe(args.shoe)
            }
        }
        if (viewModel.hasShoes()) {
            binding.noDataContainer.visibility = View.GONE
        }


        binding.listViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        primaryColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)

        addShoesToList(binding.shoeList)

        binding.addButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun isLoggedIn(): Boolean {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        return sharedPref.getBoolean(getString(R.string.saved_login_state), false)
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