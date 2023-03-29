package com.example.foodrecipipaging.di


import com.example.foodrecipipaging.BuildConfig
import com.example.foodrecipipaging.model.Foodresipi
import com.example.foodrecipipaging.model.resipi
import com.example.foodrecipipaging.util.Constant

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FoodAPI {


    @GET("recipes/{id}/information?includeNutrition=true&apiKey=0ecb521b1fdb46fb832f7a43b7ae6922")
    suspend fun getPostdata(@Path("id")number:Int): Response<Foodresipi>


//@Query("page")page:Int

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = Constant.PER_PAGE,
        @Query("offset") page: Int,
        @Query("query") query: String,
    ): Response<resipi>





//    @GET("recipes/complexSearch?apiKey=b6493d639385435a8b419ceaca42 b8e4")
//    suspend fun getCustomePost(
//        @Query("query") title:String
//    ):Response<Resipidata>


//    @GET("recipes/complexSearch?apiKey=0ecb521b1fdb46fb832f7a43b7ae6922")
//    suspend fun getDataFromAPIso(@Query("offset")page:Int): resipi

//    @GET("recipes/complexSearch?apiKey=0ecb521b1fdb46fb832f7a43b7ae6922")
//    suspend fun getDataFromAPI(@Query("query") query: String):Response<resipi>


}