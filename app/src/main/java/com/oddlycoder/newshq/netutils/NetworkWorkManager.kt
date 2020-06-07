package com.oddlycoder.newshq.netutils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class NetworkWorkManager(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams), NetworkInterface {

    private lateinit var networkUtils: NetworkUtils

    override fun doWork(): Result {
        this.isNetworkConnected(applicationContext)
        return Result.success()
    }

    override fun isNetworkConnected(context: Context) {
        networkUtils = NetworkUtils()
        networkUtils.isNetworkConnected(context)
    }

    override fun hasNetwork(): Boolean {
        TODO("Not yet implemented")
    }

}