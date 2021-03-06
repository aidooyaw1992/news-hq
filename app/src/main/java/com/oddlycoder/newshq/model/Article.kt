package com.oddlycoder.newshq.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "article_tbl")
data class ArticleTbl(
    @PrimaryKey
    val id: String,
    val url: String,
    val title: String,
    val text: String,
    val publisher: String,
    val author: String,
    val image: String,
    val date: String
)