package com.oddlycoder.newshq.model

import com.fasterxml.jackson.annotation.JsonProperty


data class News(
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("urls")
    val urls: Urls,
    @JsonProperty("articles")
    val articles: List<Article>
)

data class Urls(
    @JsonProperty("self")
    val self: String,
    @JsonProperty("next")
    val next: String,
    @JsonProperty("prev")
    val prev: String
)

data class Article(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("text")
    val text: String,
    @JsonProperty("publisher")
    val publisher: String,
    @JsonProperty("author")
    val author: String,
    @JsonProperty("image")
    val image: String,
    @JsonProperty("date")
    val date: String
)