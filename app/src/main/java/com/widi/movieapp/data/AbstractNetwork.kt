package com.widi.movieapp.data

import com.widi.movieapp.BuildConfig
import com.widi.movieapp.data.base.BaseNetwork
import com.widi.movieapp.data.interceptor.ContentTypeInterceptor
import okhttp3.OkHttpClient

abstract class AbstractNetwork<T>(): BaseNetwork<T>() {

    override fun getBaseUrl(): String = BuildConfig.BASE_URL

    override fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
//        builder.addInterceptor(ContentTypeInterceptor())
        return super.okHttpClientBuilder(builder)
    }

}