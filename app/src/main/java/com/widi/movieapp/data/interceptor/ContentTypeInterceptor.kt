package com.widi.movieapp.data.interceptor

import com.widi.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


open class ContentTypeInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val uri = request.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build()
        return chain.proceed(request.newBuilder().url(uri).build())
    }

    fun addHeader(oriRequest: Request): Request {
        return oriRequest.newBuilder().addHeader("Content-Type","application/json").build()
    }

}