package com.example.tubearhai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tubearhai.model.BeerData
import com.example.tubearhai.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

var page =0

    fun getNextPage(){
        page++
        getBeers(page)
    }


  private fun getBeers(page:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBeers(page)
        }
  }

    val beerLiveData:LiveData<List<BeerData>>
        get() = repository.beerLivedata


 }