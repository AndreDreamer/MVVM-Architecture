package com.example.mvvm_architecture.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.data.api.ApiHelper
import com.example.data.api.RetrofitBuilder
import com.example.data.model.Drink
import com.example.mvvm_architecture.databinding.FragmentCocktailBinding
import com.example.mvvm_architecture.ui.base.ViewModelFactory
import com.example.mvvm_architecture.ui.main.adapter.CocktailAdapter
import com.example.mvvm_architecture.ui.main.viewmodel.CocktailViewModel
import com.example.core.utils.Status
import com.example.domain.models.LocalDrink


class OrdinaryDrinkFragment : Fragment() {
    private lateinit var viewModel: CocktailViewModel
    private lateinit var binding: FragmentCocktailBinding
    private lateinit var adapter: CocktailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUI()
        setupObservers()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(CocktailViewModel::class.java)
    }

    private fun setupUI() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = CocktailAdapter(arrayListOf())
            recyclerView.adapter = adapter

            refreshLayout.setOnRefreshListener {
                setupObservers()
            }
        }
    }

    private fun setupObservers() {
        viewModel.listOrdinaryDrink().observe(viewLifecycleOwner, {
            with(binding) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            refreshLayout.isRefreshing = false
                            resource.data?.let { drinks -> retrieveList(drinks.drinks) }
                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            refreshLayout.isRefreshing = false
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveList(drinks: List<LocalDrink>) {
        adapter.apply {
            addDrinks(drinks)
            notifyDataSetChanged()
        }
    }
}