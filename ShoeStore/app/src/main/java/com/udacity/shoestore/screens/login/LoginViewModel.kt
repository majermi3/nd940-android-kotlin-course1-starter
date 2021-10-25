package com.udacity.shoestore.screens.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    fun isEmailValid(): Boolean {
        return !email.value.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun isPasswordValid(): Boolean {
        return !password.value.isNullOrEmpty()
    }

}