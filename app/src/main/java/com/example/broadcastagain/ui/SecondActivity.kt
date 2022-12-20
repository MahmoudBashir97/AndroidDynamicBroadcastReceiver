package com.example.broadcastagain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastagain.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        this.title = "Second Activity"
    }
}