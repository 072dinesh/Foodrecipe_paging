package com.example.foodrecipipaging

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Myapplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}