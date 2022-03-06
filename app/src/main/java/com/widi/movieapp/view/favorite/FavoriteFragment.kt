package com.widi.movieapp.view.favorite

import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class FavoriteFragment: BaseMvpFragment<FavoritePresenter>(), FavoriteContract.View {

    @Inject
    override lateinit var presenter: FavoritePresenter

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidSupportInjection.inject(this)
    }

    override fun setup() {
    }

    override fun getLayout(): Int = R.layout.fragment_favorite
}