package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()
        handler.postDelayed(
            object : Runnable {
                override fun run() {
                    startActivity(Intent(this@SplashActivity,MainPage::class.java))
                    finish()
                }
            }, 1000
        )
    }
}