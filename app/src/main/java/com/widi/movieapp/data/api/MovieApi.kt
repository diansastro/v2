package com.widi.movieapp.data.api

import com.widi.movieapp.body.MarkAsFavouriteBody
import com.widi.movieapp.data.response.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {
    @GET("trending/movie/day")
    fun getPopularMovie(): Observable<Response<PopularMovieResponse>>

    @GET("genre/movie/list")
    fun getGenreList(): Observable<Response<GenreListResponse>>

    @GET("discover/movie")
    fun getMovieByGenre(@Query("with_genres") genreId: Int): Observable<Response<MovieListResponse>>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(@Path("movie_id") movieId: Int): Observable<Response<MovieTrailerResponse>>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String): Observable<Response<MovieListResponse>>

    @POST("account/{account_id}/favorite?session_id=5b3b1fd7c6ff33e0a509eae75e45f80e6b8b129f")
    fun postFavourite(@Path("account_id") accountId: Int, @Body markAsFavourite: MarkAsFavouriteBody): Observable<Response<FavouriteResponse>>
}