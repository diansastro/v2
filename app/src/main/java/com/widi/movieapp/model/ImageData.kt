package com.widi.movieapp.model

import com.google.gson.annotations.SerializedName

data class ImageData(@SerializedName("aspect_ratio") var aspect_ratio: Number? = 0.0,
                @SerializedName("height") var height: Int? = 0,
                @SerializedName("iso_639_1") var iso_639_1: String? = "",
                @SerializedName("file_path") var file_path: String? = "",
                @SerializedName("vote_average") var vote_average: Number? = 0.0,
                @SerializedName("vote_count") var vote_count: Int? = 0,
                @SerializedName("width") var width: Int? = 0) {
}