package com.example.data.model

import com.example.domain.models.LocalDrink
import com.example.domain.models.LocalDrinkDetail
import com.example.domain.models.LocalDrinkDetailList
import com.example.domain.models.LocalDrinkList
import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("idDrink") val id: Long,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val image: String

) {
    fun convertToLocal(): LocalDrink = LocalDrink(id, name, image)
}

data class DrinkList(
    @SerializedName("drinks") val drinks: List<Drink>
) {
    fun convertToLocal(): LocalDrinkList {
        val localDrinks: List<LocalDrink> = drinks.map { it.convertToLocal() }
        return LocalDrinkList(localDrinks)
    }
}

data class DrinkDetailList(
    @SerializedName("drinks") val drinks: List<DrinkDetail>
) {
    fun convertToLocal(): LocalDrinkDetailList {
        val localDrinks: List<LocalDrinkDetail> = drinks.map { it.convertToLocal() }
        return LocalDrinkDetailList(localDrinks)
    }
}

data class DrinkDetail(
    @SerializedName("idDrink") val id: Long,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val image: String,
    @SerializedName("strInstructions") val instruction: String

) {
    fun convertToLocal(): LocalDrinkDetail = LocalDrinkDetail(id, name, image, instruction)

}
