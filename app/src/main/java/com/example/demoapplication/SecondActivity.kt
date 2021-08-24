package com.example.demoapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivitySecondBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        val inputData = intent.extras
        val input = inputData!!.getString("input")
        viewBinding.resultView.setText(input)
    }
}