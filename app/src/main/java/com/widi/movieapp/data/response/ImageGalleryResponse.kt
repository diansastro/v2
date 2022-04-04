package com.widi.movieapp.data.response

import com.google.gson.annotations.SerializedName
import com.widi.movieapp.model.ImageData

class ImageGalleryResponse(@SerializedName("backdrops") var backdrops: ArrayList<ImageData>) {
}