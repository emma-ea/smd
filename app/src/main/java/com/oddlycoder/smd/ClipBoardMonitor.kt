package com.oddlycoder.smd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "com.oddlycoder.smd"

class ClipBoardMonitor : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ClipBoardMonitor")
        context?.startService(Intent(context, ClipBoardService::class.java))
        Toast.makeText(context, "app restarted", Toast.LENGTH_SHORT).show()
    }

}