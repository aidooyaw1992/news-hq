package com.oddlycoder.newshq.model.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.ArticleTbl

@Database(entities = [ArticleTbl::class], version = 1, exportSchema = true)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticleDao

}