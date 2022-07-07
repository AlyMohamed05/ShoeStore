package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.EditShoeFragmentBinding
import com.udacity.shoestore.models.ImagesResources
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.AppViewModel
import com.udacity.shoestore.ui.MainActivity

class EditShoeFragment : Fragment() {

    private lateinit var binding: EditShoeFragmentBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.edit_shoe_fragment,
            container,
            false
        )
        viewModel = (activity as MainActivity).viewModel

        // This line adds a random image to the Shoe Item as the support to
        // allow user to choose image is not implemented yet.
        binding.shoeImage.setImageResource(ImagesResources.getRandomImageResource())

        setUpClickListeners()
        return binding.root
    }

    private fun setUpClickListeners() {
        binding.createFloatingButton.setOnClickListener {
            val message = validate()
            if (message != null) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addNewShoe(
                    Shoe(
                        name = binding.nameEdit.editText?.text.toString(),
                        size = binding.sizeEdit.editText?.text.toString().toDouble(),
                        description = binding.nameEdit.editText?.text.toString(),
                        company = binding.companyEdit.editText?.text.toString()
                    )
                )
                findNavController().navigateUp()
            }
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun validate(): String? {
        val message: String? = if (binding.nameEdit.editText?.text?.isBlank() == true) {
            "Please Enter a name"
        } else if (binding.companyEdit.editText?.text?.isBlank() == true) {
            "Please Enter company name"
        } else if (binding.sizeEdit.editText?.text?.isBlank() == true) {
            "Please Enter a Size"
        } else if (binding.descriptionEdit.editText?.text?.isBlank() == true) {
            "Please Enter a description"
        } else {
            null
        }

        return message
    }

}