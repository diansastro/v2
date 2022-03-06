package com.widi.movieapp

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.widi.movieapp.di.component.ApplicationComponent
import com.widi.movieapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class MainActivity: MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var activityDispatchAndroidInjection: DispatchingAndroidInjector<Activity>

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
        initInjection()
    }

    fun initInjection(){
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        applicationComponent.Inject(this)
    }
}