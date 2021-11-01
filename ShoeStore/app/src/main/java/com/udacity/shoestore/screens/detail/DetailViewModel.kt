package com.udacity.shoestore.screens.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class DetailViewModel : ViewModel() {
    var shoe = MutableLiveData<Shoe>()

    init {
        shoe.value = Shoe()
    }
}