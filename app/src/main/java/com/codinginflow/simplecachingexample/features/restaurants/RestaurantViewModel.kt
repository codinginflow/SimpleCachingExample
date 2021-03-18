package com.codinginflow.simplecachingexample.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.simplecachingexample.api.RestaurantApi
import com.codinginflow.simplecachingexample.data.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    api: RestaurantApi
) : ViewModel() {
        private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()
        val restaurants: LiveData<List<Restaurant>> = restaurantsLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getRestaurants()
            delay(2000)
            restaurantsLiveData.value = restaurants
        }
    }
}