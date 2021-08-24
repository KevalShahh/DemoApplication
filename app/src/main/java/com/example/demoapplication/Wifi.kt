package com.example.demoapplication

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.demoapplication.databinding.ActivityWifiBinding
import kotlinx.android.synthetic.main.activity_wifi.*


class Wifi : AppCompatActivity() {
    lateinit var viewBinding: ActivityWifiBinding
    lateinit var manager: WifiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWifiBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        manager = getSystemService(WIFI_SERVICE) as WifiManager
        viewBinding.btnEnable.setOnClickListener {
            if (manager.isWifiEnabled == true) {
                Toast.makeText(this, "wifi is already on", Toast.LENGTH_SHORT).show()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    var intent = Intent(Settings.Panel.ACTION_WIFI)
                    startActivityForResult(intent, 0)
                    Toast.makeText(this, "turning on wifi", Toast.LENGTH_SHORT).show()
                } else {
                    manager.isWifiEnabled = true
                    Toast.makeText(this, "turn on wifi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewBinding.btnDisable.setOnClickListener {
            if (manager.isWifiEnabled == false) {
                Toast.makeText(this, "wifi is already off", Toast.LENGTH_SHORT).show()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    var intent = Intent(Settings.Panel.ACTION_WIFI)
                    startActivityForResult(intent, 0)
                    Toast.makeText(this, "turn off wifi", Toast.LENGTH_SHORT).show()
                } else {
                    manager.isWifiEnabled = false
                    Toast.makeText(this, "turning off wifi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewBinding.scanBtn.setOnClickListener {
            if (manager.isWifiEnabled == false) {
                Toast.makeText(this, "please enable wifi first", Toast.LENGTH_SHORT).show()
            } else {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                }
                var devices = manager.scanResults
                var list = ArrayList<Any>()
                for (wifi in (devices as MutableList<ScanResult>)) {
                    if (wifi.SSID!=null && wifi.SSID.isNotEmpty() && wifi.SSID !in list){
                        list.add(wifi.SSID.toString())
                    }
                }
                var adapter =
                    ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list)
                viewBinding.wifiList.adapter = adapter
                Toast.makeText(this, "showing list of available devices", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}