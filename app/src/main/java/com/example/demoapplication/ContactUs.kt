package com.example.demoapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityContactUsBinding

class ContactUs : AppCompatActivity() {
    lateinit var viewBinding: ActivityContactUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityContactUsBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.imgCall.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + viewBinding.tvNumber.text.toString())
            startActivity(dialIntent)
        }
        viewBinding.progressbar.progress = 0
        viewBinding.btnStart.setOnClickListener {
            Thread {
                for (i in 0..50) {
                    try {
                        Thread.sleep(200)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    viewBinding.progressbar.progress = i
                }
            }.start()
        }
    }
}