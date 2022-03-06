package com.widi.movieapp.view.splash

import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpActivity
import com.widi.movieapp.view.MovieActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity: BaseMvpActivity<SplashPresenter>(), SplashContract.View {
    @Inject
    override lateinit var presenter: SplashPresenter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        initSplash()
    }

    override fun getLayout(): Int = R.layout.activity_splash

    fun initSplash() = addUiSubscription(
        Observable.timer(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startActivity(intentFor<MovieActivity>())
                finish()
            }
    )
}