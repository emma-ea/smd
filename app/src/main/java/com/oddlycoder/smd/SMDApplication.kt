package com.oddlycoder.smd

import android.app.Application
import android.util.Log

private const val TAG = "com.oddlycoder.smd"

class SMDApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: SMDApplication")
        ClipRepository.initialize(this)
    }

}
