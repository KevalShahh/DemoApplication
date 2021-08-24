package com.example.demoapplication

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.scan.ScanFilter
import com.polidea.rxandroidble2.scan.ScanSettings
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {
    var list = ArrayList<CustomRxBleModel>()
    private lateinit var scanSubscription: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val rxBleClient: RxBleClient = RxBleClient.create(this)
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        val REQUEST_ENABLE_BT = 1
        this.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        scanSubscription =
            rxBleClient.scanBleDevices(ScanSettings.Builder().build(), ScanFilter.empty())
                .subscribe {
                    list.add(CustomRxBleModel(it.bleDevice))
                    Log.d("TAG", "onCreate: " + it.bleDevice)
                }
        btn_rx_scan.setOnClickListener {
            val adapter = CustomRxBLEAdapter(this, list)
            rv_ble.adapter = adapter
            rv_ble.layoutManager = LinearLayoutManager(this)
            Log.d("TAG", "onCreate: " + list.size)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scanSubscription.dispose()
    }
}