package com.gb.advanced2.entities

data class Article(
    val term: String,
    val desc: String,
)

typealias Articles = ArrayList<Article>
