package com.example.shoestoreinventory

import android.os.Bundle

import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.databinding.FragmentShoeListBinding


class ShoeListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater, R.layout.fragment_shoe_list, container, false)

        (activity as AppCompatActivity).supportActionBar?.show()
        //(activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)



        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToNewShoeFragment())
        }

        // https://developer.android.com/guide/navigation/navigation-custom-back
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {
                    // do nothing.
                    // See point #15 in the homework assignment.
                    Toast.makeText(requireActivity(), "Cannot go back to onboarding from shoelistfragment.", Toast.LENGTH_SHORT).show()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId){
            R.id.logout -> findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }

}