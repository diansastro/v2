package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.widi.movieapp.model.GenreListData

class GenreListResponse(@SerializedName("genres") var genres: ArrayList<GenreListData>) {
}