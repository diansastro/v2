package com.widi.movieapp.view.detail

import com.widi.movieapp.base.ErrorView
import com.widi.movieapp.body.MarkAsFavouriteBody
import com.widi.movieapp.data.response.MovieTrailerResponse

interface MovieDetailContract {
    interface View: ErrorView {
        fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?)
        fun onSuccess()
    }

    interface Presenter {
        fun execMovieTrailer(movieId: Int)
        fun execPostFavourite(accountId: Int, favouriteBody: MarkAsFavouriteBody)
    }
}