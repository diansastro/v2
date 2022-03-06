package com.widi.movieapp.di.builder

import com.widi.movieapp.view.MovieActivity
import com.widi.movieapp.view.MovieProvider
import com.widi.movieapp.view.detail.MovieDetailActivity
import com.widi.movieapp.view.search.SearchActivity
import com.widi.movieapp.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindDetailMovieActivity(): MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [MovieProvider::class])
    abstract fun bindMovieActivity(): MovieActivity

}