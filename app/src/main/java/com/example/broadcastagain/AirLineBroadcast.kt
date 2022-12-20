package com.example.broadcastagain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirLineBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val state = intent?.getBooleanExtra("state",false)?: return
        Toast.makeText(context,"started : ",Toast.LENGTH_LONG).show()
        if (state){
            Toast.makeText(context,"is enabled",Toast.LENGTH_LONG).show()
        }else  Toast.makeText(context,"is Not enabled",Toast.LENGTH_LONG).show()
    }
}