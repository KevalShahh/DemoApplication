package com.example.demoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleConnection
import io.reactivex.disposables.Disposable

class RxDeviceControlActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable
    private lateinit var connectionState:Disposable
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_device_control)

        val deviceAddress = intent.getStringExtra("rxaddress")
        Log.d("TAG", "onCreate: $deviceAddress")
        val rxBleClient: RxBleClient = RxBleClient.create(this)
        val device = deviceAddress?.let { rxBleClient.getBleDevice(it) }
        //  var device=intent.getStringExtra("device") as RxBleDevice
        disposable = device!!.establishConnection(false)
            .subscribe(
                { rxBleConnection: RxBleConnection? ->
                    Log.d("TAG", "onCreate: connection$rxBleConnection")
                }
            ) { throwable: Throwable? ->
                Log.d("TAG", "onCreate: throws" + throwable?.printStackTrace())
            }

        connectionState=device.observeConnectionStateChanges().subscribe {
            if (it == RxBleConnection.RxBleConnectionState.CONNECTING) {
                Log.d("TAG", "onCreate: Connecting")
            }
            if (it == RxBleConnection.RxBleConnectionState.CONNECTED) {
                Log.d("TAG", "onCreate: Connected")
            }
            if (it == RxBleConnection.RxBleConnectionState.DISCONNECTING) {
                Log.d("TAG", "onCreate: Disconnecting")
            }
            if (it == RxBleConnection.RxBleConnectionState.DISCONNECTED) {
                Log.d("TAG", "onCreate: Disconnected")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        connectionState.dispose()
    }
}