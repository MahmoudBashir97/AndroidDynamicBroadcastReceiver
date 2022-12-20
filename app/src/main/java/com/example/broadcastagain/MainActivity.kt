package com.example.broadcastagain

import android.app.ActivityOptions
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.broadcastagain.ui.SecondActivity

class MainActivity : AppCompatActivity() {
    lateinit var airLineBroadcast: AirLineBroadcast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.title = "Second Activity"

        initLoading()
        initEvents()
    }
    private fun initLoading(){
        airLineBroadcast= AirLineBroadcast()
        // this is an dynamic broadcast
        //registerBroadcast()
    }
    private fun initEvents(){
        this.findViewById<Button>(R.id.nxt_btn).setOnClickListener{
            navigateToSecond()
        }
    }

    override fun onStart() {
        super.onStart()
        registerBroadcast()
    }

    override fun onResume() {
        super.onResume()
        registerBroadcast()
    }

    private fun registerBroadcast() {
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airLineBroadcast,it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airLineBroadcast)
    }
    private fun navigateToSecond(){
        val i = Intent(this,SecondActivity::class.java)
        val b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle() as Bundle
        startActivity(i,b)
    }
}