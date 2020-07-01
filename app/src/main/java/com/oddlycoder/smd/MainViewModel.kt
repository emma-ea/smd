package com.oddlycoder.smd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

private const val TAG = "com.oddlycoder.smd"

class MainViewModel : ViewModel() {

    private val repo = ClipRepository.get()

    fun getClip(): LiveData<String> {
        Log.d(TAG, "getClip: LiveData data")
        return repo.getClip()
    }

}