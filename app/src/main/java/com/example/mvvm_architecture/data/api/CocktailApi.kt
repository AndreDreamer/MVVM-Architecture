package com.example.mvvm_architecture.data.api

import com.example.mvvm_architecture.data.model.DrinkDetailList
import com.example.mvvm_architecture.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailApi {

    @GET("api/json/v1/1/filter.php?c=Ordinary_Drink")
    suspend fun listOrdinaryDrink(): DrinkList

    @GET("api/json/v1/1/filter.php?c=Cocktail")
    suspend fun listCocktail(): DrinkList

    @GET("api/json/v1/1/lookup.php?i")
    suspend fun detailDrink(@Query("i") id: String): DrinkDetailList

}