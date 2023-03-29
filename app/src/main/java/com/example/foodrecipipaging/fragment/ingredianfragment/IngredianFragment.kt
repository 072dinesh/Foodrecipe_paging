package com.example.foodrecipipaging.fragment.ingredianfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipipaging.R
import com.example.foodrecipipaging.databinding.FragmentIngredianBinding
import com.example.foodrecipipaging.fragment.searchfragment.SerchViewModel
import com.example.foodrecipipaging.util.NetworkResult
import com.example.foodrecipipaging.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredianFragment : Fragment() {

    private var _binding: FragmentIngredianBinding? = null
    private val binding get() = _binding!!
    //private var _binding: FragmentHomeBinding?= null


    private val viewmodelsecode: SerchViewModel by viewModels()
    private lateinit var adapter: IngredianAdepter
    private val args by navArgs<IngredianFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredianBinding.inflate(inflater, container, false)

        setingredients()
        setupUi()
        return binding.root
    }

    private fun setupUi() {

        args.argin.let {

            viewmodelsecode.getPost2(it)

        }
        adapter = IngredianAdepter()
        binding.recyclerviewmain.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerviewmain.adapter = adapter

    }

    private fun setingredients() {
        viewmodelsecode.allUsers.observe(viewLifecycleOwner, Observer { response ->


//                response.data?.let { recipe ->
//
//                    recipe.extendedIngredients.let { ingredients ->
//                        adapter.setData(ingredients?.filterNotNull() ?: emptyList())
//                    }
//                    binding.shimmerLayouts.stopShimmer()
//                    binding.shimmerLayouts.visibility=View.GONE
//
//                }

            when (response) {
                is NetworkResult.Error -> {
                    response.message?.snackBar(requireView(), requireContext())
                }
                is NetworkResult.Loading -> {
                    // show loading indicator or shimmer layout
                    //binding.recyclerviewmain.showShimmer() // to start showing shimmer

//                    Handler().postDelayed(Runnable {
//                        binding.recyclerviewmain.hideShimmer() // to hide shimmer
//                    } as Runnable, 3000)

                }
                is NetworkResult.Success -> {
                    response.data?.extendedIngredients.let { resci ->


                        adapter.setData(resci?.filterNotNull() ?: emptyList())
                    }
                }
            }


        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}