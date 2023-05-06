package com.example.shoestoreinventory.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestoreinventory.models.Shoe

class ShoeViewModel : ViewModel() {

    val name = MutableLiveData<String>("default name")
    val brand = MutableLiveData<String>("default brand")
    val size = MutableLiveData<String>("default size")
    val description = MutableLiveData<String>("default description")

    /*
    Kotlin has a different way of doing a ternary operator similar to the familiar
    y = (x == null) ? DEFAULT_VAL : x
    https://www.baeldung.com/kotlin/ternary-operator
     */
    fun getShoe() : Shoe{
        return Shoe(
            name.value ?: "",
            brand.value ?: "",
            size.value ?: "",
            description.value ?: ""
        )

    }

}