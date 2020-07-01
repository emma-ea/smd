package com.oddlycoder.smd

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import org.intellij.lang.annotations.RegExp

private const val TAG = "com.oddlycoder.smd"

class ClipBoardService : Service() {

    val instaLinkRegex = "instagram.com"
    val twitterLinkRegex = "twitter.com"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ClipBoardService")
        val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipBoard.addPrimaryClipChangedListener {
            Log.d(TAG, "onStartCommand: PrimaryClipChangedListener")
            val clipData = clipBoard.primaryClip?.getItemAt(0)?.text
            val gotRequired = isRequiredClip(clipData!!)
            if (gotRequired)
                Toast.makeText(this, "clip changed", Toast.LENGTH_SHORT).show()
        }
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun isRequiredClip(data: CharSequence): Boolean {
        Log.d(TAG, "isRequiredClip: Data to repository")
        val clipRepo = ClipRepository.get()

        if (data.contains(instaLinkRegex)) {
            Toast.makeText(this, "instagram link obtained", Toast.LENGTH_SHORT).show()
        }
        if (data.contains(twitterLinkRegex)) {
            Toast.makeText(this, "twitter link obtained", Toast.LENGTH_SHORT).show()
        }

        clipRepo.setClip(data.toString())
        return true
    }

}