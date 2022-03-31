package com.widi.movieapp.view.favorite

import com.widi.movieapp.base.BasePresenter
import com.widi.movieapp.data.entity.MovieEntity
import com.widi.movieapp.extension.uiSubscribe
import com.widi.movieapp.objects.NetworkCode
import javax.inject.Inject

class FavoritePresenter @Inject constructor(val movieEntity: MovieEntity): BasePresenter<FavoriteContract.View>(), FavoriteContract.Presenter {
    override fun execFavouriteMovie(accountId: Int) {
        addSubscription(movieEntity.getFavouriteMovie(accountId).uiSubscribe({
            when(it.code()) {
                NetworkCode.CODE_OK -> view?.getFavouriteMovie(it.body())
                else -> {
                    view?.errorScreen("Unable to Fetch Data")
                }
            }
        }, {
            view?.errorScreen(it.localizedMessage)
        }, {}))
    }
}