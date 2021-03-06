//Siena South-Ciero created the home fragment
package edu.uw.info448.cocktailcreations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //horizontal layout
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val newLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Adapter
        val adapter = CocktailListAdapter(this)
        val newAdapter = CocktailListAdapter(this)


        //popular cocktail
        val recyclerView = view.findViewById<RecyclerView>(R.id.popularCocktailRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        viewModel.popularCocktailData.observe(viewLifecycleOwner, Observer<List<Cocktail>> {
            adapter.submitList(it)
        })

        //new cocktail
        val newRecyclerView = view.findViewById<RecyclerView>(R.id.newCocktailRecyclerView)
        newRecyclerView.adapter = newAdapter
        newRecyclerView.layoutManager = newLayoutManager
        viewModel.newCocktailData.observe(viewLifecycleOwner, Observer<List<Cocktail>> {
            newAdapter.submitList(it)
        })

        return view
    }
}