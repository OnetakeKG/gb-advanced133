package com.gb.advanced2.app

import androidx.lifecycle.LiveData
import com.gb.advanced2.entities.Articles
import io.reactivex.Observable

class Contract {

    sealed class AppState {
        class Empty : AppState()
        class Loading : AppState()
        data class DataLoaded(val data: Articles) : AppState()
        data class Error(val error: String) : AppState()
    }

    interface ViewModel {
        fun getState(): LiveData<AppState>
        fun search(searchString: String)
    }

    interface Model {
        fun getArticles(searchString: String): Observable<Articles>
    }
}