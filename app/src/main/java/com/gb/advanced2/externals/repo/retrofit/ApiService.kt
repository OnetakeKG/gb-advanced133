package com.gb.advanced1337.externals.repo.retrofit



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") searchQuery: String): Response<List<ModelResponseEntry>>

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