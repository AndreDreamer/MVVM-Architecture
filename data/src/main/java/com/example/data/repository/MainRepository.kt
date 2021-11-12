package com.example.data.repository

import com.example.data.api.ApiHelper

import com.example.domain.models.LocalDrinkDetailList
import com.example.domain.models.LocalDrinkList
import com.example.domain.repository.DrinkRepository

class MainRepository(private val apiHelper: ApiHelper) : DrinkRepository {

    override suspend fun detailDrink(id: String): LocalDrinkDetailList {
        return apiHelper.detailDrink(id).convertToLocal()
    }

    override suspend fun listCocktail(): LocalDrinkList {
        return apiHelper.listCocktail().convertToLocal()

    }

    override suspend fun listOrdinaryDrink(): LocalDrinkList {
        return apiHelper.listOrdinaryDrink().convertToLocal()

    }
}