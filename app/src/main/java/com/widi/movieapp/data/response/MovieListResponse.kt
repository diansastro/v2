package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.widi.movieapp.model.MovieData

class MovieListResponse(@SerializedName("page") var page: Int? = 0,
                        @SerializedName("results") var results: ArrayList<MovieData>,
                        @SerializedName("total_pages") var total_pages: Int? = 0,
                        @SerializedName("total_results") var total_results: Int? = 0) {
}