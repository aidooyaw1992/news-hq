package com.oddlycoder.newshq

import android.app.Application
import com.oddlycoder.newshq.model.data.remote.NewsBuilder
import com.oddlycoder.newshq.model.repository.Repository

class ApplicationContext : Application() {

    override fun onCreate() {
        super.onCreate()
        NewsBuilder.initialize(this)
        Repository.initialize(this)
    }
}