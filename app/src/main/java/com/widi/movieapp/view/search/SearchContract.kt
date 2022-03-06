package com.widi.movieapp.view.search

import com.widi.movieapp.base.ErrorView
import com.widi.movieapp.data.response.MovieListResponse

interface SearchContract {
    interface View: ErrorView {
        fun searchMovie(movieListResponse: MovieListResponse?)
    }

    interface Presenter {
        fun execSearchMovie(query: String)
    }
}