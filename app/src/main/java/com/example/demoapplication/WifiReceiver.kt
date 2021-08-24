package com.example.demoapplication

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class WifiReceiver(private var manager: WifiManager, private var wifiDeviceList: ListView?): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
            val sb = StringBuilder()
            val wifiList: List<ScanResult> = manager.scanResults
            val deviceList: ArrayList<String> = ArrayList()
            for (scanResult in wifiList) {
                sb.append("\n").append(scanResult.SSID).append(" - ")
                    .append(scanResult.capabilities)
                deviceList.add(scanResult.SSID.toString() + " - " + scanResult.capabilities)
            }
            Toast.makeText(context, sb, Toast.LENGTH_SHORT).show()
            val arrayAdapter: ArrayAdapter<*> =
                ArrayAdapter(context!!, R.layout.simple_list_item_1, deviceList.toArray())
            wifiDeviceList?.adapter = arrayAdapter
    }
}
