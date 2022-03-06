package com.widi.movieapp.di.component

import android.app.Application
import com.widi.movieapp.MainActivity
import com.widi.movieapp.di.builder.ActivityBuilder
import com.widi.movieapp.di.module.ApplicationModule
import com.widi.movieapp.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    NetModule::class,
    ActivityBuilder::class,
    AndroidInjectionModule::class
))

interface ApplicationComponent {

    fun Inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}