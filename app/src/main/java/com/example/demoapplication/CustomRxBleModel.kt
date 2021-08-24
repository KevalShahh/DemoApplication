package com.example.demoapplication

import com.polidea.rxandroidble2.RxBleDevice

class CustomRxBleModel(var RxBleAddress:RxBleDevice) {
    fun getRxAddress(): RxBleDevice {
        return this.RxBleAddress
    }
    fun setRxAddress(RxBle: RxBleDevice){
        this.RxBleAddress=RxBle
    }
}
