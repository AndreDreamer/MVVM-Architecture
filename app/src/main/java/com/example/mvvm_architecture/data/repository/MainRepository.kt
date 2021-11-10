package com.example.mvvm_architecture.data.repository

import com.example.mvvm_architecture.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun detailDrink(id: String) = apiHelper.detailDrink(id)
    suspend fun listCocktail() = apiHelper.listCocktail()
    suspend fun listOrdinaryDrink() = apiHelper.listOrdinaryDrink()

}