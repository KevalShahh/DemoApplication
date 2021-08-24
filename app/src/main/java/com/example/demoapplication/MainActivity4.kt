package com.example.demoapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main4.*
import kotlin.random.Random

class MainActivity4 : AppCompatActivity() {
    lateinit var all: ArrayList<View>
    lateinit var array: ArrayList<View>
    lateinit var id: View

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        all=ArrayList()
        all.add(btn1)
        all.add(btn2)
        all.add(btn3)
        all.add(btn4)
        all.add(btn5)
        all.add(btn6)
        all.add(btn7)
        all.add(btn8)
        all.add(btn9)

        array = ArrayList()
        btn5.setBackgroundColor(R.color.blue)
        btn5.setOnClickListener {
            all.remove(btn5)
            views(btn5)
        }
        btn1.setOnClickListener {
            views(btn1)
        }
        btn2.setOnClickListener {
            views(btn2)
        }
        btn3.setOnClickListener {
            views(btn3)
        }
        btn4.setOnClickListener {
            views(btn4)
        }
        btn6.setOnClickListener {
            views(btn6)
        }
        btn7.setOnClickListener {
            views(btn7)
        }
        btn8.setOnClickListener {
            views(btn8)
        }
        btn9.setOnClickListener {
            views(btn9)
        }

    }

    @SuppressLint("ResourceAsColor")
    fun views(it: View) {
        if(all.isEmpty()){
            it.setBackgroundColor(R.color.black)
            it.setClickable(false)
            it.setEnabled(false)
            Toast.makeText(this, "puzzle success", Toast.LENGTH_SHORT).show()
        }
        else {
            var a = Random.nextInt(all.size)
            id = all[a]
            Log.d("TAG", "onCreate: $it:" + id)
            all.removeAt(a)
            id.setBackgroundColor(R.color.teal_200)
            id.setClickable(true)
            id.setEnabled(true)
            it.setBackgroundColor(R.color.black)
            it.setClickable(false)
            it.setEnabled(false)
        }
    }
}