package com.example.demoapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import com.example.demoapplication.databinding.ActivityBrightnessBinding

class Brightness : AppCompatActivity() , SensorEventListener {
    lateinit var sensorManager: SensorManager
    var brightness: Sensor? =null
    lateinit var viewBinding:ActivityBrightnessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityBrightnessBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpSensorStuff()
    }

    private fun setUpSensorStuff() {
        sensorManager= getSystemService(SENSOR_SERVICE) as SensorManager
        brightness=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type  == Sensor.TYPE_LIGHT){
            val light=event.values[0]
            viewBinding.tvSensor.text = light.toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,brightness,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}