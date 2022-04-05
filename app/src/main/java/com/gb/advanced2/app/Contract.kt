package com.gb.advanced1337.app

import androidx.lifecycle.LiveData
import com.gb.advanced1337.entities.Articles
import io.reactivex.Observable

class Contract {
    sealed class AppState {
        class Empty : AppState()                               // nothing loaded yet
        class Loading : AppState()                             // showing progress bar
        data class DataLoaded(val data: Articles) : AppState()
        data class Error(val error: String) : AppState()
    }
    interface ViewModel {
        fun getState(): LiveData<AppState>
        fun search(searchString: String)
    }

    interface Model {
        suspend fun getArticles(searchString: String): Articles
    }
}