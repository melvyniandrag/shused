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
import com.example.shoestoreinventory.viewmodels.ShoeViewModel

class NewShoeFragment : Fragment(){
    private lateinit var viewModel : ShoeListViewModel
    private lateinit var  shoeViewModel : ShoeViewModel
    private lateinit var binding : FragmentNewShoeBinding
    private lateinit var shoe : Shoe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(activity as AppCompatActivity).get(ShoeListViewModel::class.java)
        shoeViewModel = ViewModelProvider(activity as AppCompatActivity).get(ShoeViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_new_shoe, container, false)

        binding.shoeViewModel = shoeViewModel

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            viewModel.addShoe(shoeViewModel.getShoe())
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        return binding.root
    }

}