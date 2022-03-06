package com.widi.movieapp.base

abstract class BaseMvpActivity<T: BasePresenter<*>>: BaseActivity() {
    protected abstract var presenter: T

    override fun internalSetup() {
        initPresenterView()
        super.internalSetup()
    }

    abstract fun initPresenterView()

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}