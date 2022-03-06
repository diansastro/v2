package com.widi.movieapp.view.detail

import com.widi.movieapp.base.BasePresenter
import com.widi.movieapp.data.entity.MovieEntity
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
}