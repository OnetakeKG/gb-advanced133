package com.gb.advanced2.app

import com.gb.advanced2.entities.Articles
import io.reactivex.Observable

class Contract {

    interface View {
        fun displayArticles(articles: Articles)
        fun showError(error: String)
        fun showLoadingScreen()
    }

    interface Presenter {
        fun onAttachView(view: View)
        fun onDetachView()
        fun search(term: String)
    }

    interface Model {
        fun getArticles(searchString: String): Observable<Articles>
    }
}