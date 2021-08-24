package com.example.demoapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIObject {
    companion object {
        private var foodAPI: FoodAPI? = null
        val instance: FoodAPI?
            get() {
                if (foodAPI == null) {
                    val retrofit: Retrofit =
                        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("https://api.androidhive.info/").build()
                    foodAPI = retrofit.create(FoodAPI::class.java)
                }
                return foodAPI
            }
    }
}
