package com.widi.movieapp.view.search

import com.widi.movieapp.base.BasePresenter
import com.widi.movieapp.data.entity.MovieEntity
import com.widi.movieapp.extension.uiSubscribe
import com.widi.movieapp.objects.NetworkCode
import javax.inject.Inject

class SearchPresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<SearchContract.View>(), SearchContract.Presenter {
    override fun execSearchMovie(query: String) {
        addSubscription(movieEntity.searchMovie(query).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.searchMovie(it.body())
                else -> {
                    view?.errorScreen("Unable to Load Data")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}