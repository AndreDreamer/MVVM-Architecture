package com.example.mvvm_architecture.data.api


class ApiHelper(private val apiService: CocktailApi) {

    suspend fun detailDrink(id: String) = apiService.detailDrink(id)
    suspend fun listCocktail() = apiService.listCocktail()
    suspend fun listOrdinaryDrink() = apiService.listOrdinaryDrink()
}