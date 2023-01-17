package com.example.aac_mvvm_retrofit_kotlin.ui

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}