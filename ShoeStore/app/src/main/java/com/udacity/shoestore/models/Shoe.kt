package com.udacity.shoestore.models

import android.os.Parcelable
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import kotlinx.android.parcel.Parcelize
import timber.log.Timber

@Parcelize
data class Shoe(
    var name: String = "",
    var size: Double = 0.0,
    var company: String = "",
    var description: String = "",
    val images: List<String> = mutableListOf()
) : Parcelable {

    fun hasName(): Boolean {
        return !name.isNullOrEmpty()
    }
    fun hasCompany(): Boolean {
        return !company.isNullOrEmpty()
    }

}