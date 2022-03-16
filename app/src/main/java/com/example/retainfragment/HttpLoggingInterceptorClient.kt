package com.example.retainfragment

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptorClient {
    val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                redactHeader("Authorization")
                redactHeader("Cookie")
            }
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
}