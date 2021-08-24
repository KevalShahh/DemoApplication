package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var viewBinding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMain2Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.tvGetname.setText(intent.getStringExtra("Name"))
        viewBinding.tvGetage.setText(intent.getStringExtra("Age"))
    }
}