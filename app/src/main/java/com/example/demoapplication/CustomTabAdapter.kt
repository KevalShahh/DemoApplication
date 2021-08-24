package com.example.demoapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomTabAdapter(var context: Context, var supportFragmentManager: FragmentManager,var tabCount: Int) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return LoginFragment()
            }
            1->{
                return SignupFragment()
            }
            2->{
                return DesignFragment()
            }
            else->{
                return LoginFragment()
            }
        }
    }

}
