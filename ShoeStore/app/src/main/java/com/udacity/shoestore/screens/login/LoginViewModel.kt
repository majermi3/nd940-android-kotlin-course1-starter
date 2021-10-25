package com.udacity.shoestore.screens.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var passwordRepeat = MutableLiveData<String>()

    fun isEmailValid(): Boolean {
        return !email.value.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun isPasswordValid(): Boolean {
        return !password.value.isNullOrEmpty()
    }

    fun isPasswordRepeatValid(): Boolean {
        return !passwordRepeat.value.isNullOrEmpty() && password.value.equals(passwordRepeat.value)
    }

    fun isNameValid(): Boolean {
        return !name.value.isNullOrEmpty()
    }

}