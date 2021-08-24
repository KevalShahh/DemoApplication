package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.demoapplication.databinding.ActivityUiDesignBinding

class UiDesign : AppCompatActivity() {
    lateinit var viewBinding:ActivityUiDesignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityUiDesignBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
    }
}