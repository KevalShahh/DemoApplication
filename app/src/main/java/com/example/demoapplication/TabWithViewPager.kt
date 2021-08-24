package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityTabWithViewPagerBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.view.*

class TabWithViewPager : AppCompatActivity() {
    lateinit var viewBinding: ActivityTabWithViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTabWithViewPagerBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        var adapter = CustomTabAdapter(this, supportFragmentManager, viewBinding.tabLayout.tabCount)
        viewBinding.viewPager.adapter = adapter
        viewBinding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(viewBinding.tabLayout))
        viewBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewBinding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}