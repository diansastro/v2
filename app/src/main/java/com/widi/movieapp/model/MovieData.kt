package com.widi.movieapp.model

import com.google.gson.annotations.SerializedName

class MovieData(@SerializedName("adult") var adult: Boolean? = false,
                @SerializedName("backdrop_path") var backdrop_path: String? = "",
                @SerializedName("genre_ids") var genre_ids: ArrayList<Int>,
                @SerializedName("id") var id: Int? = 0,
                @SerializedName("original_language") var original_language: String? = "",
                @SerializedName("original_title") var original_title: String? = "",
                @SerializedName("overview") var overview: String? = "",
                @SerializedName("popularity") var popularity: Number? = 0,
                @SerializedName("poster_path") var poster_path: String? = "",
                @SerializedName("release_date") var release_date: String? = "",
                @SerializedName("title") var title: String? = "",
                @SerializedName("video") var video: Boolean? = false,
                @SerializedName("vote_average") var vote_average: Number? = 0.0,
                @SerializedName("vote_count") var vote_count: Int? = 0) {
}