package com.widi.movieapp.view.favorite

import com.widi.movieapp.base.ErrorView
import com.widi.movieapp.data.response.FavouriteMovieResponse

interface FavoriteContract {
    interface View: ErrorView {
        fun getFavouriteMovie(favouriteMovieResponse: FavouriteMovieResponse?)
    }
    interface Presenter {
        fun execFavouriteMovie(accountId: Int)
    }
}