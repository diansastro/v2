package com.widi.movieapp.data.entity

import android.content.Context
import com.widi.movieapp.data.BasicAbstractNetwork
import com.widi.movieapp.data.api.MovieApi
import com.widi.movieapp.data.response.*
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MovieEntity @Inject constructor(context: Context): BasicAbstractNetwork<MovieApi>(context) {
    override fun getApi(): Class<MovieApi> = MovieApi::class.java

    fun getPopularMovie(): Observable<Response<PopularMovieResponse>> = networkService().getPopularMovie()

    fun getGenreList(): Observable<Response<GenreListResponse>> = networkService().getGenreList()

    fun getMovieByGenre(genreId: Int): Observable<Response<MovieListResponse>> = networkService().getMovieByGenre(genreId)

    fun getMovieTrailer(movieId: Int): Observable<Response<MovieTrailerResponse>> = networkService().getMovieTrailer(movieId)

    fun searchMovie(query: String): Observable<Response<MovieListResponse>> = networkService().searchMovie(query)
}