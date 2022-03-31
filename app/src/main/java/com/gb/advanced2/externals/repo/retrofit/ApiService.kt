package com.gb.advanced2.externals.repo.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") searchQuery: String): Observable<List<ModelResponseEntry>>

    companion object {
        const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}

data class ModelResponseEntry(
    val text: String?,
    val meanings: List<ModelMeaning>?,
)

data class ModelMeaning(
    val translation: ModelTranslation?,
)

data class ModelTranslation(
    val text: String?,
    val note: String?,
)