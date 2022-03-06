package com.widi.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieTrailerData(@SerializedName("iso_639_1") var iso_639_1: String? = "",
                       @SerializedName("iso_3166_1") var iso_3166_1: String? = "",
                       @SerializedName("name") var name: String? = "",
                       @SerializedName("key") var key: String? = "",
                       @SerializedName("site") var site: String? = "",
                       @SerializedName("size") var size: Int? = 0,
                       @SerializedName("type") var type: String? = "",
                       @SerializedName("official") var official: Boolean? = false,
                       @SerializedName("published_at") var published_at: String? = "",
                       @SerializedName("id") var id: String? = "") {
}