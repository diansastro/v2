package com.widi.movieapp.model

import com.google.gson.annotations.SerializedName

class AuthorDetailsData(@SerializedName("name") var name: String? = "",
                        @SerializedName("username") var username: String? = "",
                        @SerializedName("avatar_path") var avatar_path: String? = "",
                        @SerializedName("rating") var rating: Int? = 0) {
}