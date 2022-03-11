package com.widi.movieapp.body

import com.google.gson.annotations.SerializedName

class MarkAsFavouriteBody(@SerializedName("media_type") var media_type: String? = "",
                          @SerializedName("media_id") var media_id: Int? = 0,
                          @SerializedName("favorite") var favorite: Boolean? = false) {
}