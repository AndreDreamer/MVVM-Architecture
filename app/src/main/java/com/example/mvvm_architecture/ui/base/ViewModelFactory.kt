package com.example.mvvm_architecture.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.api.ApiHelper
import com.example.data.repository.MainRepository
import com.example.domain.usecase.drink.DrinkUseCase
import com.example.mvvm_architecture.ui.main.viewmodel.CocktailViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
            return CocktailViewModel(DrinkUseCase(MainRepository(apiHelper))) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}