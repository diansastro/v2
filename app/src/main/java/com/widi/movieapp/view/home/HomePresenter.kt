package com.widi.movieapp.view.home

import com.widi.movieapp.base.BasePresenter
import com.widi.movieapp.data.entity.MovieEntity
import com.widi.movieapp.extension.uiSubscribe
import com.widi.movieapp.objects.NetworkCode
import javax.inject.Inject

class HomePresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun execPopularMovie() {
        addSubscription(movieEntity.getPopularMovie().uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getPopularMovie(it.body())
                else -> {
                    view?.errorScreen("Unable to Load Data")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}))
    }

    override fun execGenreList() {
        addSubscription(movieEntity.getGenreList().uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getGenreList(it.body())
                else -> {
                    view?.errorScreen("Unable to Load Data")
                }
            }
        }, {
           view?.errorScreen(it.localizedMessage)
        }, {}))
    }

    override fun execMovieByGenre(genreId: Int) {
        addSubscription(movieEntity.getMovieByGenre(genreId).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getMovieByGenre(it.body())
                else -> {
                    view?.errorScreen("Unable to Fetch Data")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}