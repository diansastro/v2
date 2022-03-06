package com.widi.movieapp.model

import com.google.gson.annotations.SerializedName

data class GenreListData(@SerializedName("id") var id: Int? = 0,
                         @SerializedName("name") var name: String? = "") {
}