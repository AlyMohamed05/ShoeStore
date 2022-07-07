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

    // EditShoeFragment Section
    // It has LiveData objects to handle all operations related to adding new Shoe item

    // LiveData objects bound to Edit Text views in EditShoeFragment
    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()

    // LiveData objects to send messages to show in Toast to EditShoeFragment
    private val _validationMessages = MutableLiveData<String?>()
    val validationMessages: LiveData<String?>
        get() = _validationMessages

    // LiveData object to let EditFragment know that the job is finished
    private val _finishedStatus = MutableLiveData(false)
    val finishedStatus: LiveData<Boolean>
        get() = _finishedStatus

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

    fun addNewShoe() {
        val validationMessage = validateNewShoeInput()
        if (validationMessage != null) {
            // Inputs are not valid and we should show the message
            _validationMessages.value = validationMessage
        } else {
            // We can create a new shoe with the current data
            val shoe = Shoe(
                name = shoeName.value.toString(),
                size = shoeSize.value.toString().toDouble(),
                company = shoeCompany.value.toString(),
                description = shoeDescription.value.toString()
            )
            _shoesList.value?.add(shoe)
            _shoesList.value = _shoesList.value
            finishedEditingNewShoe()
        }
    }

    // This function sets the finished status to close EditShoeFragment
    // The reason to extract this line and not hard code it is to bind this function
    // to the cancel button.
    fun finishedEditingNewShoe() {
        _finishedStatus.value = true
        clearEditFields()
    }

    fun clearValidationMessages() {
        _validationMessages.value = null
    }

    fun clearFinishStatus() {
        _finishedStatus.value = false
    }

    // This function will automatically handle the case where data is not valid.
    // So if returns true it means that we can just Shoe
    // If returns false, then we can't and we don't need to handle this case,
    // it's
    private fun validateNewShoeInput(): String? {
        val message: String? = if (shoeName.value?.isBlank() == true || shoeName.value == null) {
            "Please Enter a name"
        } else if (shoeCompany.value?.isBlank() == true || shoeCompany.value == null) {
            "Please Enter company name"
        } else if (shoeSize.value?.isBlank() == true || shoeSize.value == null) {
            "Please Enter a Size"
        } else if (shoeDescription.value?.isBlank() == true || shoeDescription.value == null) {
            "Please Enter a description"
        } else {
            null
        }
        return message
    }

    // Resets The Data of Edit Text in EditShoeFragment
    private fun clearEditFields(){
        shoeName.value = ""
        shoeSize.value = ""
        shoeCompany.value = ""
        shoeDescription.value =""
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("AppViewModel is cleared")
    }

}