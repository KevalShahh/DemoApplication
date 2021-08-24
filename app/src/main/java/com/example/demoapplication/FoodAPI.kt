package com.example.demoapplication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface FoodAPI {
    @GET("json/shimmer/menu.php")
    fun getMenu(): Call<List<FoodModel>>
    /*@POST("json/shimmer/menu.php")
    fun post(@Field("user") id:String):Call<List<FoodModel>>*/
}
