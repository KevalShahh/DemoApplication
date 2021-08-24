package com.example.demoapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.viewpager.clipToPadding=false
        viewBinding.viewpager.pageMargin=40

        var adapter=CustomPageAdapter(this,supportFragmentManager)
        viewBinding.viewpager.adapter=adapter

        viewBinding.tab.setupWithViewPager(viewBinding.viewpager, true)
    }
}
