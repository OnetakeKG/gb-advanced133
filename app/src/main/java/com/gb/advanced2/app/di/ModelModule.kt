package com.gb.advanced2.app.di

import com.gb.advanced2.app.Contract
import com.gb.advanced2.externals.repo.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelModule {
    @Provides
    @Singleton
    fun provideModel() : Contract.Model = RemoteRepository()
}