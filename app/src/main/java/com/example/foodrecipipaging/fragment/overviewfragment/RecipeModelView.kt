package com.example.foodrecipipaging.fragment.overviewfragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.foodrecipipaging.R
import com.example.foodrecipipaging.model.resipi
import com.example.foodrecipipaging.repository.FoodRepository
import com.example.foodrecipipaging.util.BaseViewModel
import com.example.foodrecipipaging.util.Constant
import com.example.foodrecipipaging.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class RecipeModelView @Inject constructor(val recipeRepository: FoodRepository) : ViewModel(){

    private var query: String = ""

    var recipes = Pager(
        config = PagingConfig(pageSize = Constant.PER_PAGE, prefetchDistance = 2),
        pagingSourceFactory = {
            RecipePagingSource(recipeRepository, query = query)
        }
    ).flow.cachedIn(viewModelScope)


    fun searchRecipe(query: String){
        this.query = query
    }






}