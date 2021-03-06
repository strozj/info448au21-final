package edu.uw.info448.cocktailcreations
/*
    Contributors: Jacob Strozyk, Siena South-Ciero, Sarah West
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList


private const val TAG = "CocktailListAdapter"

class CocktailListAdapter(val context: Fragment) : ListAdapter<Cocktail, CocktailListAdapter.ViewHolder>(CocktailDiffCallback()) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cocktailName: TextView = view.findViewById(R.id.cocktailListName)
        val cocktailImg: ImageView = view.findViewById(R.id.cocktailListImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cocktail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        //Cocktail cards
        val cocktailImg = item.image
        val cocktailName = item.name
        val directions = item.instructions
        var recipe = item.recipe
        holder.cocktailName.text = item!!.name
        Glide.with(context).load("$cocktailImg").into(holder.cocktailImg)

        //handle navigation
        holder.cocktailImg.setOnClickListener {
            if (recipe != null) {
                val argBundle = Bundle()
                argBundle.putString("cocktailName", cocktailName)
                argBundle.putString("cocktailImg", cocktailImg)
                argBundle.putString("directions", directions)

                argBundle.putParcelableArrayList("recipe", recipe as ArrayList<Ingredient>?)
                it.findNavController().navigate(R.id.action_to_RecipeFragment, argBundle)
            } else {
                Toast.makeText(holder.itemView.context, "Sorry, Viewing cocktail details not supported in" +
                        " ingredient search mode because the API is janky.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class CocktailDiffCallback : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }
}