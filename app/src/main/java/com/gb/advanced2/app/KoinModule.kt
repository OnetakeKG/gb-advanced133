package com.gb.advanced1337.app

import com.gb.advanced1337.adapters.MainViewModel
import com.gb.advanced1337.externals.repo.RemoteRepository
import org.koin.dsl.module

val koinModule = module {
    single<Contract.Model> { RemoteRepository() }
    single<Contract.ViewModel> { MainViewModel(model = get()) }
}