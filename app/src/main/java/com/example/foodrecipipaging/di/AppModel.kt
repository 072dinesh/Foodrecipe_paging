package com.example.foodrecipipaging.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {


    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    @Singleton
    @Provides
    fun provideRetrofitInstance(

        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(
                "https://api.spoonacular.com"


            )
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }



    @Singleton
    @Provides
    fun provideStarterTemplateApiService(retrofit: Retrofit): FoodAPI {
        return retrofit.create(FoodAPI::class.java)
    }


//    @Singleton
//    @Provides
//    fun provideDashboardDataSource(dashboardApi: RetroServieInstance): RetroRepository {
//        return RetroRepository(dashboardApi)
//    }
}