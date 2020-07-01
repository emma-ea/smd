package com.oddlycoder.smd

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

private const val TAG = "com.oddlycoder.smd"

class MainActivity : AppCompatActivity() {

    private var receiver: ClipBoardMonitor? = null
    
    private val intentFilter: IntentFilter by lazy {
        IntentFilter().apply { 
            addAction(Intent.ACTION_BOOT_COMPLETED)
        }
    }

    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: MainActivity")

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel!!.getClip().observe(this, Observer {
            Log.d(TAG, "onCreate: Observing data")
            findViewById<TextView>(R.id.clip_text).text = it
        })

        receiver = ClipBoardMonitor()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: context receiver active")
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: context receiver disabled")
        unregisterReceiver(receiver)
    }
}