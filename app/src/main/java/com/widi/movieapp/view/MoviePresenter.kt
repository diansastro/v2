package com.widi.movieapp.view

import com.widi.movieapp.base.BasePresenter
import javax.inject.Inject

class MoviePresenter @Inject constructor(): BasePresenter<MovieContract.View>(), MovieContract.Presenter {
}