package com.maverick.api

import com.maverick.api.services.ConduitAPI
import com.maverick.api.services.ConduitAuthAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ConduitClient {

    var authToken: String? = null

    //using interceptor we can pass user login token in api so that we don't have to pass token everytime we call the api function.
    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization", "Token $it")
                .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.realworld.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())

    val publicApi = retrofit
        .client(okHttpBuilder.build())
        .build()
        .create(ConduitAPI::class.java)

    val authApi = retrofit
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(ConduitAuthAPI::class.java)
}