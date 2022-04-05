package com.gb.advanced1337.entities

data class Article(
    val term: String,
    val desc: String,
)

typealias Articles = ArrayList<Article>
