package com.oddlycoder.newshq

import android.app.Application
import com.oddlycoder.newshq.model.repository.Repository

class ApplicationContext : Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * room requires context.
         * get application context on app startup for room
         * */
        Repository.initialize(this)
    }
}