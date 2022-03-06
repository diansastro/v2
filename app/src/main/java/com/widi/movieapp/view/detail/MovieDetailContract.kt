package com.widi.movieapp.view.detail

import com.widi.movieapp.base.ErrorView
import com.widi.movieapp.data.response.MovieTrailerResponse

class MovieDetailContract {
    interface View: ErrorView {
        fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?)
    }

    interface Presenter {
        fun execMovieTrailer(movieId: Int)
    }
}