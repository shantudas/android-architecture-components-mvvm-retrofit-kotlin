package com.example.aac_mvvm_retrofit_kotlin.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Empty<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data, null)

    @Suppress("UNUSED_PARAMETER")
    class Error<T>(message: String, data: T? = null) : Resource<T>(null, message)
}