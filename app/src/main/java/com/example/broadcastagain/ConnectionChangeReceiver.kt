package com.example.broadcastagain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class ConnectionChangeReceiver:BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == "someAction"){

            val connectivity =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNet = connectivity.activeNetwork
            val isConnected = activeNet != null

            if (isConnected)
                Toast.makeText(context,"Connected Successfully!",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context,"Failed !!",Toast.LENGTH_SHORT).show()
        }
    }
}