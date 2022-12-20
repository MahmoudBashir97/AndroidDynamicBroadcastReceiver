package com.example.broadcastagain.ui

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.broadcastagain.ConnectionChangeReceiver
import com.example.broadcastagain.R

class SecondActivity : AppCompatActivity() {
    lateinit var receiver: ConnectionChangeReceiver
    lateinit var intentFilter: IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        this.title = "Second Activity"
        initLoading()
        initEvents()
    }

    private fun initLoading() {
        receiver = ConnectionChangeReceiver()
        intentFilter=  IntentFilter("someAction")
        registerReceiver(receiver,intentFilter)
    }

    private fun initEvents() {
        this.findViewById<Button>(R.id.check_btn)
            .setOnClickListener {
                sendBroadcast(Intent("someAction"))
            }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver,intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver,intentFilter)
    }
}