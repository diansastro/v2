package com.widi.movieapp.view.home

import com.widi.movieapp.base.ErrorView
import com.widi.movieapp.data.response.GenreListResponse
import com.widi.movieapp.data.response.MovieListResponse
import com.widi.movieapp.data.response.PopularMovieResponse

interface HomeContract {
    interface View: ErrorView  {
        fun getPopularMovie(popularMovieResponse: PopularMovieResponse?)
        fun getGenreList(genreListResponse: GenreListResponse?)
        fun getMovieByGenre(movieListResponse: MovieListResponse?)
    }
    interface Presenter {
        fun execPopularMovie()
        fun execGenreList()
        fun execMovieByGenre(genreId: Int)
    }
}