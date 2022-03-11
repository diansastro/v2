package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName

class FavouriteResponse(@SerializedName("success") var success: Boolean? = false,
                        @SerializedName("status_code") var status_code: Int? = 0,
                        @SerializedName("status_message") var status_message: String? = "") {
}