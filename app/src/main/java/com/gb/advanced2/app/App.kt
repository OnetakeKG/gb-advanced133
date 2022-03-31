package com.gb.advanced2.app

import android.app.Application
import com.gb.advanced2.adapters.Presenter
import com.gb.advanced2.externals.repo.RemoteRepository

class App : Application() {
    val model: Contract.Model = RemoteRepository()
    val presenter: Contract.Presenter = Presenter(model)

    init { instance = this }

    companion object {
        var instance: App? = null
    }
}