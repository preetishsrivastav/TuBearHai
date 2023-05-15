package com.example.tubearhai.view.network

import com.example.tubearhai.model.BeerData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {

    @GET("/v2/beers")
   suspend fun getBeers(@Query("page") page:Int):Response<List<BeerData>>

}