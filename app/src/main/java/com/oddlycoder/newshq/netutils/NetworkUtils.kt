package com.oddlycoder.newshq.netutils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkUtils : NetworkInterface {
    
    private var hasNetwork: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(context: Context) {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivity.activeNetwork

        val networkHasConnection =
            connectivity.getNetworkCapabilities(activeNetwork)

        hasNetwork = networkHasConnection != null &&
                networkHasConnection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun hasNetwork(): Boolean {
        return hasNetwork
    }

}
