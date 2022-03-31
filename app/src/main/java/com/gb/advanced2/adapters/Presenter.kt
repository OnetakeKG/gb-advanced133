package com.gb.advanced2.adapters

import com.gb.advanced2.app.Contract
import com.gb.advanced2.entities.Articles
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Presenter(private val model: Contract.Model) : Contract.Presenter {
    private var view: Contract.View? = null
    private var disposable: Disposable? = null
    private var currentArticles: Articles? = null

    override fun onAttachView(view: Contract.View) {
        this.view = view
        view.displayArticles(currentArticles ?: Articles())
    }

    override fun onDetachView() {
        view = null
        disposable?.apply { dispose() }
        disposable = null
    }

    override fun search(term: String) {
        view?.let { v ->
            v.showLoadingScreen()
            disposable = model.getArticles(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { onLoadingError(it) }
                .subscribe { onDataReady(it) }
        }
    }

    private fun onDataReady(articles: Articles) = view?.apply { displayArticles(articles) }
    private fun onLoadingError(error: Throwable?) =
        view?.apply { showError(error?.toString() ?: "Unknown error") }
}