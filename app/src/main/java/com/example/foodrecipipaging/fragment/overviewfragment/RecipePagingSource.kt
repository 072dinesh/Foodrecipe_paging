package com.example.foodrecipipaging.fragment.overviewfragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodrecipipaging.model.Result
import com.example.foodrecipipaging.di.FoodAPI
import com.example.foodrecipipaging.repository.FoodRepository
import okhttp3.internal.notifyAll
import timber.log.Timber


class RecipePagingSource (val quoteAPI: FoodRepository, private val query:String) : PagingSource<Int, com.example.foodrecipipaging.model.Result>(){

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
//anchorposition use index previous and next page
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }


    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.foodrecipipaging.model.Result> {


        return try {
            var nextPageNumber = params.key ?: 0
            var nextKey = params.key?.plus(1) ?: 1

            when(params){
                is LoadParams.Refresh -> {
                    nextPageNumber = 0
                    nextKey = 1
                }
                else -> Unit
            }

            val response = quoteAPI.getRecipes(nextPageNumber, query)
            if(response.isSuccessful){
                if(response.body()!= null) {
                    LoadResult.Page(
                        data = response.body()!!.results?.filterNotNull()?: emptyList(),
                        prevKey = null,
                        nextKey = if(nextKey == response.body()?.totalResults) null else nextKey + 10

                    )
                } else {
                    LoadResult.Error(Exception())
                }
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }



    }
}