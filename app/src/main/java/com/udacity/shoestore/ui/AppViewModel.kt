package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoesDB
import timber.log.Timber

class AppViewModel : ViewModel() {

    // Shoe List Data
    private val _shoesList = MutableLiveData(mutableListOf<Shoe>())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        Timber.d("AppViewModel is initialized")
        _shoesList.value = ShoesDB.shoeList
    }

    // This function is used to validate email and password fields
    // If All input fields are valid returns null.
    // else it will return error message to show in a toast.
    fun validate(binding: FragmentLoginBinding): String? {
        val message: String? =
            if (binding.emailEdit.text?.isBlank() == true) {
                "Please enter an email"
            } else if (binding.emailEdit.text.toString().find { it == '@' } == null) {
                "Please enter a valid email"
            } else if (binding.passwordEdit.text?.isBlank() == true) {
                "Please enter a password"
            } else {
                null
            }
        return message
    }

    fun addNewShoe(shoe: Shoe){
        _shoesList.value?.add(shoe)
        _shoesList.value = _shoesList.value
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("AppViewModel is cleared")
    }

}