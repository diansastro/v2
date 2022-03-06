package com.widi.movieapp.extension

import android.widget.EditText

fun EditText.isEmpty(): Boolean {
    return this.text.isNullOrEmpty()
}