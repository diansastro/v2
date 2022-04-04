package com.widi.movieapp.view.detail

import com.widi.movieapp.base.BasePresenter
import com.widi.movieapp.body.MarkAsFavouriteBody
import com.widi.movieapp.data.entity.MovieEntity
import com.widi.movieapp.extension.getErrorMessage
import com.widi.movieapp.extension.uiSubscribe
import com.widi.movieapp.objects.NetworkCode
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {
    override fun execMovieTrailer(movieId: Int) {
        addSubscription(movieEntity.getMovieTrailer(movieId).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getMovieTrailer(it.body())
                else -> {
                    view?.errorScreen("Unable to Load Data")
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }

    override fun execPostFavourite(accountId: Int, favouriteBody: MarkAsFavouriteBody) {
        addSubscription(movieEntity.execMarkFavourite({
            when(it.code()) {
                NetworkCode.STATUS_CODE -> view?.onSuccess()
                NetworkCode.STATUS_CODE_BAD_REQUEST -> {
                    val error = it.errorBody()?.toString()
                    view?.errorScreen(error?.getErrorMessage(it.message()), it.code())
                }
                else -> {
                    view?.errorScreen("Unable to send data, Try Again")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}, accountId, favouriteBody))
    }

    override fun execMovieGallery(movieId: Int) {
        addSubscription(movieEntity.getImageGallery(movieId).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getMovieGallery(it.body())
                else -> {
                    view?.errorScreen("Unable to Load Data")
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}