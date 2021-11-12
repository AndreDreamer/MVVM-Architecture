package com.example.domain.usecase.drink

import com.example.domain.repository.DrinkRepository

class DrinkUseCase(private val repository: DrinkRepository) {

    suspend fun detailDrink(id: String) = repository.detailDrink(id)

    suspend fun listCocktail() = repository.listCocktail()

    suspend fun listOrdinaryDrink() = repository.listOrdinaryDrink()

}