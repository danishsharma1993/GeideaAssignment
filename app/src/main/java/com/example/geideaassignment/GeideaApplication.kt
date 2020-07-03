package com.example.geideaassignment

import android.app.Application

class GeideaApplication : Application() {
    companion object {
        lateinit var instance: GeideaApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}