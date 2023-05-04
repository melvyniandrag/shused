package com.example.shoestoreinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.databinding.FragmentNewShoeBinding
import com.example.shoestoreinventory.models.Shoe
import com.example.shoestoreinventory.viewmodels.ShoeListViewModel

class NewShoeFragment : Fragment(){
    private lateinit var viewModel : ShoeListViewModel
    private lateinit var binding : FragmentNewShoeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_shoe, container, false)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            // update the view model
            val shoe = Shoe(
                name=binding.shoeNameEntry.text.toString(),
                brand = binding.shoeBrandEntry.text.toString(),
                size=binding.shoeSizeEntry.text.toString(),
                description = binding.shoeDescriptionEntry.text.toString())
            viewModel.addShoe(shoe)
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        return binding.root
    }

}