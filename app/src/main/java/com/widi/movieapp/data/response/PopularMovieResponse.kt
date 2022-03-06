package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.widi.movieapp.model.PopularMovieData

class PopularMovieResponse(@SerializedName("page") var page: Int? = 0,
                           @SerializedName("results") var results: ArrayList<PopularMovieData>,
                           @SerializedName("total_pages") var total_pages: Int? = 0,
                           @SerializedName("total_results") var total_results: Int? = 0) {
}