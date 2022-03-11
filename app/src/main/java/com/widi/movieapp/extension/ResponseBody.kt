package com.widi.movieapp.extension

import org.json.JSONObject

fun String.getErrorMessage(default: String) : String? {
    var message: String? = null
    var jsonObject: JSONObject? = null
    try {
        jsonObject = JSONObject(this)
    } catch (e: Exception) {
    }
    return jsonObject.getErrorMessage(default)
}

fun JSONObject?.getErrorMessage(default: String): String? {
    var message: String? = null
    if (message.isNullOrEmpty()) {
        try {
            this?.let {
                message = it.getJSONObject("meta").getString("message")
            }
        } catch (e: Exception) {
        }
    }
    if (message.isNullOrEmpty()) {
        message = default
    }
    return message
}