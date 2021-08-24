package com.example.demoapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomPageAdapter(var context: Context,var fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5
    }
    override fun getPageWidth(position: Int): Float {
        return 0.9f
    }
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return Fragment1()
            }
            1 -> {
                return Fragment2()
            }
            2 -> {
                return Fragment3()
            }
            3->{
                return Fragment4()
            }
            4->{
                return Fragment5()
            }
            else -> {
                return Fragment1()
            }
        }
    }


}
