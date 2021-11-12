package com.example.mvvm_architecture.ui.details.view

import android.os.Bundle
import android.os.LocaleList
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.data.api.ApiHelper
import com.example.data.api.RetrofitBuilder
import com.example.data.model.DrinkDetail
import com.example.mvvm_architecture.databinding.ActivityDetailsBinding
import com.example.mvvm_architecture.ui.base.ViewModelFactory
import com.example.mvvm_architecture.ui.main.viewmodel.CocktailViewModel
import com.example.core.utils.Status
import com.example.domain.models.LocalDrinkDetail
import com.squareup.picasso.Picasso

class DetailsActivity : FragmentActivity() {
    private lateinit var viewModel: CocktailViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        val index = intent.getLongExtra("DRINK_KEY", 0)
        setupObservers(index)

    }

    private fun setupUI() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(CocktailViewModel::class.java)
    }

    private fun setupObservers(id: Long) {
        viewModel.detailDrink(id.toString()).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { drink -> showDrink(drink.drinks[0]) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun showDrink(drinkDetail: LocalDrinkDetail) {
        with(binding) {
            Picasso.with(titleTextView.context)
                .load(drinkDetail.image)
                .into(imageView)
            titleTextView.text = drinkDetail.name
            instructionTextView.text = drinkDetail.instruction
        }
    }


}