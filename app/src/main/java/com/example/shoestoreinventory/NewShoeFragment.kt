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
    private lateinit var shoe : Shoe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(activity as AppCompatActivity).get(ShoeListViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_shoe, container, false)

        shoe = Shoe("name", "brand", "size", "description")
        binding.myNewShoe = shoe

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            binding.apply{
                myNewShoe?.name = shoeNameEntry.text.toString()
                myNewShoe?.brand = shoeBrandEntry.text.toString()
                myNewShoe?.size  = shoeSizeEntry.text.toString()
                myNewShoe?.description = shoeDescriptionEntry.text.toString()
            }
            viewModel.addShoe(shoe)
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        return binding.root
    }

}