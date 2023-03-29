package com.example.foodrecipipaging.fragment.overviewfragment

import android.app.DownloadManager.Query
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.foodrecipipaging.R
import com.example.foodrecipipaging.databinding.FragmentRecipeBinding
import com.example.foodrecipipaging.util.NetworkResult
import com.example.foodrecipipaging.util.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private var _binding:FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    //lateinit var recyclerView: RecyclerView
    private val ViewModelRec: RecipeModelView by viewModels()
    private lateinit var adapter: RecipeAdapter

    private var lastquery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater,container,false)

        adapter = RecipeAdapter( onMainClick = {
            // it.id.toString()
            it.id?.let {
                //Toast.makeText(requireContext(), "${it.id.toString()}", Toast.LENGTH_LONG).show()
                var action = RecipeFragmentDirections.actionRecipeFragmentToSearchFragment(it)
                findNavController().navigate(action)
            }

        },)
        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(

            header = LoaderAdapater(),
            footer = LoaderAdapater()
        )
        setUpUi()
        setoverview()
        return binding.root
    }

    private fun setUpUi(){
//    = binding.etMain.setOnSearchClickListener {
//        ViewModelRec.searchRecipe(it?.toString() ?: "")
//        binding.recyclerview.scrollToPosition(0)
//        adapter.refresh()
//
//            var list = ArrayList<com.example.foodrecipipaging.model.Result>()

        binding.etMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isNullOrEmpty() && query != lastquery) {
                     lastquery = query
                    ViewModelRec.searchRecipe(query.toString())
                    adapter.refresh()
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isNullOrEmpty() && newText != lastquery) {
                    lastquery = newText
                    ViewModelRec.searchRecipe(newText.toString())
                    adapter.refresh()
                }
                return true
            }
        })



    }





    private fun setoverview(){

        lifecycleScope.launch {
            ViewModelRec.recipes.collectLatest {
                adapter.submitData(it)
            }
        }
    }

}