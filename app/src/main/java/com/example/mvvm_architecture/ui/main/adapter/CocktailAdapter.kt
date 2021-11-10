package com.example.mvvm_architecture.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_architecture.R
import com.example.mvvm_architecture.data.model.Drink
import com.example.mvvm_architecture.ui.details.view.DetailsActivity
import com.squareup.picasso.Picasso

class CocktailAdapter(private val drinks: ArrayList<Drink>) :
    RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = drinks[position]
        holder.bindNote(itemNote)
    }

    override fun getItemCount() = drinks.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var drink: Drink? = null
        lateinit var titleTextView: TextView
        lateinit var image: ImageView

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val myIntent = Intent(context, DetailsActivity::class.java)
            myIntent.putExtra("DRINK_KEY", drink?.id)
            context.startActivity(myIntent)
        }

        fun bindNote(drink: Drink) {
            this.drink = drink
            titleTextView = view.findViewById(R.id.titleView)
            image = view.findViewById(R.id.imageView3)
            Picasso.with(titleTextView.context)
                .load(drink.image)
                .into(image)

            titleTextView.text = drink.name

        }
    }

    fun addDrinks(users: List<Drink>) {
        this.drinks.apply {
            clear()
            addAll(users)
        }
    }
}