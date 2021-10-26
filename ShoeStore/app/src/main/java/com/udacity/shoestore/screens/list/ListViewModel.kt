package com.udacity.shoestore.screens.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListViewModel : ViewModel() {

    var shoes = MutableLiveData<ArrayList<Shoe>>()

    init {
        shoes.value = ArrayList<Shoe>()
        addShoe(Shoe("Air", 46.0, "Addidas", "Light shoes"))
        addShoe(Shoe("Plus", 35.0, "Nike", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."))
        addShoe(Shoe("Plus", 34.0, "Nike", "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."))
        addShoe(Shoe("Plus", 33.0, "Nike", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
        addShoe(Shoe("Plus", 33.0, "Nike", "Sport shoes"))
    }

    fun addShoe(shoe: Shoe) {
        shoes.value?.add(shoe)
    }

    fun removeShoe(shoe: Shoe) {
        shoes.value?.remove(shoe)
    }
}