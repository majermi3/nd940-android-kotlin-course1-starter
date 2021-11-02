package com.udacity.shoestore.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListViewModel : ViewModel() {

    var shoes = MutableLiveData<ArrayList<Shoe>>()

    init {
        shoes.value = ArrayList<Shoe>()
    }

    fun hasShoes(): Boolean {
        return shoes.value!!.isNotEmpty()
    }

    fun addShoe(shoe: Shoe) {
        shoes.value?.add(shoe)
    }

    fun removeShoe(shoe: Shoe) {
        shoes.value?.remove(shoe)
    }
}