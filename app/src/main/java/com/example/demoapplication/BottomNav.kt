package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.demoapplication.databinding.ActivityBottomNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView


class BottomNav : AppCompatActivity() {
    lateinit var viewBinding: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBottomNavBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btm.add(MeowBottomNavigation.Model(1, R.drawable.ic_baseline_account_circle_24))
        viewBinding.btm.add(MeowBottomNavigation.Model(2, R.drawable.ic_baseline_approval_24))
        viewBinding.btm.add(MeowBottomNavigation.Model(3, R.drawable.ic_baseline_message_24))
        viewBinding.btm.setOnClickMenuListener {

        }
        viewBinding.btm.setOnShowListener {

        }


        supportFragmentManager.beginTransaction().replace(R.id.cons_bottom, LoginFragment())
            .commit()
        viewBinding.bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.item_login -> {
                    removeBadge()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.cons_bottom, LoginFragment()).commit()
                }
            }
            true
        }
        viewBinding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_login -> {
                    addBadge("1")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.cons_bottom, LoginFragment()).commit()
                }
                R.id.item_signup -> {
                    removeBadge()
                    viewBinding.bottomNav.getOrCreateBadge(R.id.item_signup).isVisible = true
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.cons_bottom, SignupFragment()).commit()
                }
                R.id.item_profile -> {
                    removeBadge()
                    viewBinding.bottomNav.removeBadge(R.id.item_signup)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.cons_bottom, ProfileFragment()).commit()
                }
                R.id.item_design -> {
                    removeBadge()
//                    viewBinding.bottomNav.setBadge(R.id.item_design,99)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.cons_bottom, DesignFragment()).commit()
                }
            }
            true
        }

    }

    var notificationsBadge: View? = null
    private fun getBadge(): View {
        if (notificationsBadge != null) {
            return notificationsBadge!!
        }
        val mbottomNavigationMenuView =
            viewBinding.bottomNav.getChildAt(0) as BottomNavigationMenuView
        notificationsBadge = LayoutInflater.from(this).inflate(
            R.layout.custom_badge_layout,
            mbottomNavigationMenuView, false
        )
        return notificationsBadge!!
    }

    private fun addBadge(count: String) {
        getBadge()
        notificationsBadge?.findViewById<TextView>(R.id.notifications_badge)?.text = count
        var menu: BottomNavigationMenuView =
            viewBinding.bottomNav.getChildAt(0) as BottomNavigationMenuView
        var item: BottomNavigationItemView = menu.getChildAt(0) as BottomNavigationItemView
        item.addView(notificationsBadge)
    }

    private fun removeBadge() {
        var menu: BottomNavigationMenuView =
            viewBinding.bottomNav.getChildAt(0) as BottomNavigationMenuView
        var item: BottomNavigationItemView = menu.getChildAt(0) as BottomNavigationItemView
        item.removeView(notificationsBadge)
    }
    /*fun BottomNavigationView.setBadge(tabResId: Int, badgeValue: Int) {
        getOrCreateBadge(this, tabResId)?.let { badge ->
            badge.visibility = if (badgeValue > 0) {
                badge.text = "$badgeValue"
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun getOrCreateBadge(bottomBar: View, tabResId: Int): TextView? {
        val parentView = bottomBar.findViewById<ViewGroup>(tabResId)
        return parentView?.let {
            var badge = parentView.findViewById<TextView>(R.id.menuItemBadge)
            if (badge == null) {
                LayoutInflater.from(parentView.context).inflate(R.layout.custom_badge_layout, parentView, true)
                badge = parentView.findViewById(R.id.menuItemBadge)
            }
            badge
        }
    }*/
}