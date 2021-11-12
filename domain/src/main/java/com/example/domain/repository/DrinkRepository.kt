package com.example.domain.repository

import com.example.domain.models.LocalDrinkDetailList
import com.example.domain.models.LocalDrinkList

interface DrinkRepository {

    suspend fun detailDrink(id: String): LocalDrinkDetailList
    suspend fun listCocktail(): LocalDrinkList
    suspend fun listOrdinaryDrink(): LocalDrinkList

}