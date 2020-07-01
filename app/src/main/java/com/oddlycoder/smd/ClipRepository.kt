package com.oddlycoder.smd

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.lang.IllegalStateException

private const val TAG = "com.oddlycoder.smd"

class ClipRepository private constructor(val context: Context){

    companion object {

        private var INSTANCE: ClipRepository? = null

        fun initialize(context: Context) {
            Log.d(TAG, "initialize: repository")
            if (INSTANCE == null) {
                INSTANCE = ClipRepository(context)
            }
        }

        fun get(): ClipRepository {
            Log.d(TAG, "get: ClipRepository getter")
            return INSTANCE ?: throw IllegalStateException("cannot access repository")
        }
    }

    private var clip = MutableLiveData<String>()

    fun setClip(data: String) {
        Log.d(TAG, "setClip: data")
        clip.value = data
    }

    fun getClip(): MutableLiveData<String> {
        Log.d(TAG, "getClip: MutableLiveData clip")
        return clip
    }
}