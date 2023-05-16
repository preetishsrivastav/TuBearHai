package com.example.tubearhai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tubearhai.model.BeerData
import com.example.tubearhai.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBeers(1)
        }
    }

    val beerLiveData:LiveData<List<BeerData>>
        get() = repository.beerLivedata

}