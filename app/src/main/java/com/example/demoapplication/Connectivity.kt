package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityConnectivityBinding

class Connectivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityConnectivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityConnectivityBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnBluetooth.setOnClickListener {
            startActivity(Intent(this,Bluetooth::class.java))
        }
        viewBinding.btnWifi.setOnClickListener {
            startActivity(Intent(this,Wifi::class.java))
        }
        viewBinding.btnSensor.setOnClickListener {
            startActivity(Intent(this,Brightness::class.java))
        }
        viewBinding.btnRx.setOnClickListener {
            startActivity(Intent(this,MainActivity3::class.java))
        }
    }
}