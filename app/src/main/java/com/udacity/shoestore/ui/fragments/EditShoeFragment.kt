package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.EditShoeFragmentBinding
import com.udacity.shoestore.models.ImagesResources
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.AppViewModel
import com.udacity.shoestore.ui.MainActivity

class EditShoeFragment : Fragment() {

    private lateinit var binding: EditShoeFragmentBinding
    val viewModel: AppViewModel by activityViewModels()

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
        // This line adds a random image to the Shoe Item as the support to
        // allow user to choose image is not implemented yet.
        binding.shoeImage.setImageResource(ImagesResources.getRandomImageResource())
        binding.viewModel = viewModel

        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.validationMessages.observe(viewLifecycleOwner, Observer { message ->
            handleValidationMessage(message)
        })
        viewModel.finishedStatus.observe(viewLifecycleOwner, Observer { finished ->
            if (finished) {
                // Editing is done and we need to navigate up and clear finish status
                viewModel.clearFinishStatus()
                findNavController().navigateUp()
            }
        })
    }

    private fun handleValidationMessage(message: String?) {
        if (message != null) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            viewModel.clearValidationMessages()
        }
    }
}