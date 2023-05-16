package com.example.tubearhai.repository

import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tubearhai.databinding.ActivityMainBinding
import com.example.tubearhai.model.BeerData
import com.example.tubearhai.view.network.BeerApi

class Repository(private val beerApi: BeerApi) {

    private val beerMutableLiveData = MutableLiveData<List<BeerData>>()

     val beerLivedata:LiveData<List<BeerData>>
         get() = beerMutableLiveData

    suspend fun getBeers(page :Int){
       val result = beerApi.getBeers(page)

        if (result.body() != null){
                beerMutableLiveData.postValue(result.body())
        }

    }

}