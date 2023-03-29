package com.example.foodrecipipaging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

import com.example.foodrecipipaging.di.FoodAPI
import com.example.foodrecipipaging.fragment.overviewfragment.RecipePagingSource
import com.example.foodrecipipaging.model.Foodresipi
import com.example.foodrecipipaging.model.resipi
import retrofit2.Response
import javax.inject.Inject


class FoodRepository @Inject constructor(private val retroInstance: FoodAPI) {

//    //val lists = MutableLiveData<Foodresipi>()
//    var getQuotes = Pager(
//        config = PagingConfig(pageSize = 10, maxSize = 100),
//
//        pagingSourceFactory = {RecipePagingSource(retroInstance)}
//    ).liveData

    suspend fun getRecipes(page: Int, query:String): Response<resipi>{
        return retroInstance.getRecipes(page = page, query = query)
    }






    suspend fun getPost2(id: Int): Response<Foodresipi> {
        return retroInstance.getPostdata(id)
    }

}