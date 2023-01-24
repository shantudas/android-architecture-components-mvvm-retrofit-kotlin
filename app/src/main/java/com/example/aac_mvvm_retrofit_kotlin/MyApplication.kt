package com.example.aac_mvvm_retrofit_kotlin

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}