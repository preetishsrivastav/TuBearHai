package com.example.tubearhai.model

data class BeerData(
    val id:Int,
    val name:String,
    val tagline:String,
    val first_brewed:String,
    val description:String,
    val image_url:String,
    val food_pairing:List<String>
)