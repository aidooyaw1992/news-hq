package com.oddlycoder.newshq.netutils

import android.content.Context

interface NetworkInterface {

    fun isNetworkConnected(context: Context): Boolean

}