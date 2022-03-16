package com.example.retainfragment

import okhttp3.OkHttpClient

object LoggingInterceptorClient {
    val client: OkHttpClient
        get() = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).build()
}