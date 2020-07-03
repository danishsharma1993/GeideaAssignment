package com.example.geideaassignment.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder


class TimerService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Handler().postDelayed({
            var intent : Intent = Intent("com.geidea.update_receiver")
            sendBroadcast(intent)
        }, 5000)

        return START_STICKY
    }
}