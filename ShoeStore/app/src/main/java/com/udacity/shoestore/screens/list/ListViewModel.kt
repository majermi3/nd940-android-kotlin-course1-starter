package com.udacity.shoestore.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ListViewModel : ViewModel() {

    var shoes = MutableLiveData<ArrayList<Shoe>>()

    init {
        Timber.i(shoes.value?.size.toString())
        shoes.value = ArrayList<Shoe>()
    }

    fun addShoe(shoe: Shoe) {
        shoes.value?.add(shoe)
    }

    fun removeShoe(shoe: Shoe) {
        shoes.value?.remove(shoe)
    }
}