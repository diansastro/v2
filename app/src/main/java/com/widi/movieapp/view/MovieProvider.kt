package com.widi.movieapp.view

import com.widi.movieapp.view.favorite.FavoriteFragment
import com.widi.movieapp.view.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieProvider {
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideFavoriteFragment(): FavoriteFragment
}