package com.gb.advanced2.app.di

import com.gb.advanced2.adapters.MainViewModel
import com.gb.advanced2.app.Contract
import com.gb.advanced2.externals.repo.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideMainViewModel(model: Contract.Model): Contract.ViewModel = MainViewModel(model)
}