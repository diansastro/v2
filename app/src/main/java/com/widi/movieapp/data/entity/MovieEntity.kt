package com.widi.movieapp.data.entity

import android.content.Context
import com.widi.movieapp.body.MarkAsFavouriteBody
import com.widi.movieapp.data.BasicAbstractNetwork
import com.widi.movieapp.data.api.MovieApi
import com.widi.movieapp.data.response.*
import com.widi.movieapp.extension.uiSubscribe
import com.widi.movieapp.model.RequestTokenData
import com.widi.movieapp.objects.NetworkCode
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

class MovieEntity @Inject constructor(context: Context): BasicAbstractNetwork<MovieApi>(context) {
    override fun getApi(): Class<MovieApi> = MovieApi::class.java

    fun getPopularMovie(): Observable<Response<PopularMovieResponse>> = networkService().getPopularMovie()

    fun getGenreList(): Observable<Response<GenreListResponse>> = networkService().getGenreList()

    fun getMovieByGenre(genreId: Int): Observable<Response<MovieListResponse>> = networkService().getMovieByGenre(genreId)

    fun getMovieTrailer(movieId: Int): Observable<Response<MovieTrailerResponse>> = networkService().getMovieTrailer(movieId)

    fun searchMovie(query: String): Observable<Response<MovieListResponse>> = networkService().searchMovie(query)

    fun requestNewToken(): Observable<Response<RequestTokenData>> = networkService().requestNewToken()

    fun postFavourite(accountId: Int, markAsFavourite: MarkAsFavouriteBody): Observable<Response<FavouriteResponse>> = networkService().postFavourite(accountId, markAsFavourite)

    fun execMarkFavourite(onNext: (Response<FavouriteResponse>) -> Unit = {},
                          onError: (Throwable) -> Unit = {},
                          onComplete: () -> Unit = {},
                          accountId: Int,
                          markAsFavourite: MarkAsFavouriteBody): Disposable {
        return postFavourite(accountId, markAsFavourite).uiSubscribe({
            onNext.invoke(it)
        }, onError, onComplete)
    }

    fun getFavouriteMovie(accountId: Int): Observable<Response<FavouriteMovieResponse>> = networkService().getFavouriteMovie(accountId)
}