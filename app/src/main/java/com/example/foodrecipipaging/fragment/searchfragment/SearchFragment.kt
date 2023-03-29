package com.example.foodrecipipaging.fragment.searchfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipipaging.model.Foodresipi
import com.example.foodrecipipaging.R

import com.example.foodrecipipaging.databinding.FragmentSearchBinding
import com.example.foodrecipipaging.util.NetworkResult
import com.example.foodrecipipaging.util.snackBar
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding?=null
    private val binding get() = _binding!!
    private val searchViewModel : SerchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter
    private val args by navArgs<SearchFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        setupUi()
        settupoverview()
        return binding.root

    }

    private fun settupoverview() {


        searchViewModel.allUsers.observe(viewLifecycleOwner, Observer { resp ->

            when (resp) {
                is NetworkResult.Error -> {
                    resp.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {
                    // show loading indicator or shimmer layout
                }
                is NetworkResult.Success -> {
                    resp.data.let {

                        binding.showdata = it
                        binding.tv = searchViewModel

                        val rating: Float = it?.healthScore!!.toFloat() * 5 / 100
                        binding.rating.rating = rating

                        resp.data.let {
                            binding.showdata = it
                            it?.extendedIngredients.let {
                                adapter.setData(it!!.filterNotNull())
                            }


                        }


                        it?.id?.let { it ->
                            binding.tvSeeDetailRecipes.setOnClickListener { res ->
                                var action =
                                    SearchFragmentDirections.actionSearchFragmentToIngredianFragment(it)

                                findNavController().navigate(action)

                            }
                        }

                    }

                }
            }

            })

    }

    private fun setupUi() {

        args.args.let {

            searchViewModel.getPost2(it)

        }
        binding.backbtn.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter = SearchAdapter()
        binding.res.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.res.adapter = adapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}