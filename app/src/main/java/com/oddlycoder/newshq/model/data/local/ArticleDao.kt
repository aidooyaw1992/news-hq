package com.oddlycoder.newshq.model.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.ArticleTbl

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article_tbl")
    fun queryAllArticles(): LiveData<List<Article>>

    /** if conflict on insert, replace */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoArticles(articleTbl: ArticleTbl): Long

}