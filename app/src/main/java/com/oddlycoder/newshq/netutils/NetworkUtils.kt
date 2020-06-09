package com.oddlycoder.newshq.netutils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkUtils : NetworkInterface {
    
    private var hasNetwork: Boolean = false

    /**
     * user's network state
     */

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkHasConnection =
            connectivity.getNetworkCapabilities(connectivity.activeNetwork)

        hasNetwork = networkHasConnection != null &&
                networkHasConnection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        return hasNetwork
    }

}
