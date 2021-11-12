package com.example.domain.models

data class LocalDrink(
    val id: Long,
    val name: String,
    val image: String

)

data class LocalDrinkList(
    val drinks: List<LocalDrink>
)

data class LocalDrinkDetailList(
    val drinks: List<LocalDrinkDetail>
)

data class LocalDrinkDetail(
    val id: Long,
    val name: String,
    val image: String,
    val instruction: String

)
