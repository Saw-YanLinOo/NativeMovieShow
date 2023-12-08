package com.yanyan.movieshowclone.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHelper(private val context: Context) {

    fun isNetworkConnect():Boolean{
        var isNetworkConnect = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork?:return false
            val networkCapabilities=   connectivityManager.getNetworkCapabilities(network)?: return false
            isNetworkConnect = when{
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else -> false
            }
        }else{
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    isNetworkConnect = isConnected
                }
            }
        }

        return  isNetworkConnect
    }
}