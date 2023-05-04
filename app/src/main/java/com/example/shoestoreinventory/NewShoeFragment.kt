package com.example.shoestoreinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.databinding.FragmentNewShoeBinding

class NewShoeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewShoeBinding>(
            inflater, R.layout.fragment_new_shoe, container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            // update the view model
            findNavController().navigate(NewShoeFragmentDirections.actionNewShoeFragmentToShoeListFragment())
        }

        return binding.root
    }

}