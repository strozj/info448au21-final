package edu.uw.info448.cocktailcreations

//Siena South-Ciero and Sarah West worked on this fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable




private const val TAG = "Recipe Card"

class RecipeCardFragment : Fragment() {

    private var cocktailName: String? = null
    private var cocktailImg: String? = null
    private var recipe: List<Ingredient>? = null
    private var directions: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktailName = it.getString("cocktailName")
            cocktailImg = it.getString("cocktailImg")
            recipe = it.getParcelableArrayList("recipe")
            directions = it.getString("directions")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_recipe_card, container, false)

        rootView.findViewById<TextView>(R.id.recipeCardName).text = cocktailName
        Glide.with(this).load("$cocktailImg").into(rootView.findViewById(R.id.recipeCardImg))
        val size = recipe!!.size - 1
        var result = ""
        for(i in 0 .. size) {
            val index = recipe!![i]
            if(index.measurement != null) {
                result += index.measurement + " "
            }
            result += index.ingredientName + "\n"
        }
        rootView.findViewById<TextView>(R.id.recipeList).text = result
        rootView.findViewById<TextView>(R.id.directions_text).text = directions

        //like button
        val heartBtn = rootView.findViewById<ImageView>(R.id.recipeHeartBtn)
        heartBtn.setOnClickListener() {
            val res = resources.getDrawable(R.drawable.empty_heart)
            Log.v(TAG, "$res")
            Log.v(TAG, "$heartBtn.resources")
            if (heartBtn.resources == res) {
                heartBtn.setImageResource(R.drawable.filled_heart);
            } else {
                heartBtn.setImageResource(R.drawable.empty_heart);
            }
        }
        return rootView
    }
}