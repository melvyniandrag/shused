package com.example.shoestoreinventory

import android.os.Bundle

import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestoreinventory.databinding.FragmentShoeListBinding
import com.example.shoestoreinventory.models.Shoe
import com.example.shoestoreinventory.viewmodels.ShoeListViewModel


class ShoeListFragment : Fragment() {

    private lateinit var binding : FragmentShoeListBinding
    private lateinit var viewModel : ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        // from documentation: if livedata already has data set, it will be sent to the observer.
        // weird! I thought the observe was just for events.
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            for( shoe in it ){
               addShoeView(shoe)
            }
        })

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

    private fun addShoeView(shoe: Shoe){
        val parentLayout = binding.shoeListLinearLayout
        val shoeListLayout = layoutInflater.inflate(R.layout.list_view_item, parentLayout, false)
        val name = shoeListLayout.findViewById<TextView>(R.id.list_view_item_name)
        name.text = shoe.name
        val brand = shoeListLayout.findViewById<TextView>(R.id.list_view_item_brand)
        brand.text = shoe.brand
        val shoeSize = shoeListLayout.findViewById<TextView>(R.id.list_view_item_size)
        shoeSize.text = shoe.size
        val shoeDescription = shoeListLayout.findViewById<TextView>(R.id.list_view_item_description)
        shoeDescription.text = shoe.description
        binding.shoeListLinearLayout.addView(shoeListLayout)
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