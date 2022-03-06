package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.widi.movieapp.model.MovieTrailerData

class MovieTrailerResponse(@SerializedName("id") var id: Int? = 0,
                           @SerializedName("results") var results: ArrayList<MovieTrailerData>) {
}