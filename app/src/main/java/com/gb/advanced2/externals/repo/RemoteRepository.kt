package com.gb.advanced2.externals.repo

import com.gb.advanced2.app.Contract
import com.gb.advanced2.entities.Article
import com.gb.advanced2.entities.Articles
import com.gb.advanced2.externals.repo.retrofit.ApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository : Contract.Model {
    private val service: ApiService = makeRetrofit()
    private fun makeRetrofit(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
        return retrofit.create(ApiService::class.java)
    }
    override fun getArticles(searchString: String): Observable<Articles> =
        service.search(searchString).map { entries ->
            val out = Articles()
            for (entry in entries) {
                if (entry.text == null || entry.meanings == null)
                    continue
                val sb = StringBuilder()
                for (meaning in entry.meanings) {
                    val trans = meaning.translation ?: continue
                    if (sb.isNotEmpty()) {
                        sb.append("; ")
                    }
                    trans.text?.let { sb.append(it) }

                    if (trans.note != null && trans.note != "") {
                        sb.append(" (${trans.note})")
                    }
                }
                out.add(Article(entry.text, sb.toString()))
            }
            out
        }
}