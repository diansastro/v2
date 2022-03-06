package com.widi.movieapp.data.base

import retrofit2.Retrofit

class RetrofitHelper() {

    companion object {
        var retrofit: Retrofit?= null

        fun init(retrofit: Retrofit){
            Companion.retrofit = retrofit
        }
    }
}