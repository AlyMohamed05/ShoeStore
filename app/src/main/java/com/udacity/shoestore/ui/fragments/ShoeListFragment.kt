package com.udacity.shoestore.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.ImagesResources
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.AppViewModel
import com.udacity.shoestore.ui.MainActivity

class ShoeListFragment : Fragment() {

    private lateinit var binding: ShoeListFragmentBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )
        binding.addFloatingButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToEditShoeFragment())
        }
        viewModel = (activity as MainActivity).viewModel
        initShoeListListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenu()
    }

    private fun initShoeListListener() {
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                createShoeView(shoe)
            }
        })
    }

    // Creates a shoe card view and binds it to Linear layout
    private fun createShoeView(shoe: Shoe) {
        val view = ShoeItemBinding.inflate(
            (activity as MainActivity).layoutInflater,
            binding.linearLayout,
            true
        )
        view.titleText.text = shoe.name
        view.sizeText.text = shoe.size.toString()
        view.companyText.text = shoe.company
        view.descriptionText.text = shoe.description
        view.itemImage.setImageResource(ImagesResources.getRandomImageResource())
    }

    private fun initMenu() {
        activity?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.shoe_list_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment2())
                    return true
                }
            },
            viewLifecycleOwner
        )
    }

}