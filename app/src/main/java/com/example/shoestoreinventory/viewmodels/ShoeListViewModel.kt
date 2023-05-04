package com.example.shoestoreinventory.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestoreinventory.models.Shoe

class ShoeListViewModel : ViewModel() {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes : LiveData<MutableList<Shoe>>
        get() = _shoes
}