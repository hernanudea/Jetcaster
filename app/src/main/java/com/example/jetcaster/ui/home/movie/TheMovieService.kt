package com.example.jetcaster.ui.home.movie

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TheMovieService : Service() {

    private val TAG: String = "TheMovieService"

    init {
        Log.d(TAG, "Service running")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "on Create")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "on Destroy")
    }

}