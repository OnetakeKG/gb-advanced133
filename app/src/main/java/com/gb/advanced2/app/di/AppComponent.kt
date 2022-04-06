package com.gb.advanced2.app.di

import com.gb.advanced2.externals.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ModelModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}