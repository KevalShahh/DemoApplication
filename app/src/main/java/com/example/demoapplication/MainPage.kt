package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityMainPageBinding
import kotlinx.android.synthetic.main.activity_bluetooth.*
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlin.random.Random

class MainPage : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainPageBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnViewpager.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        viewBinding.btnSnackbar.setOnClickListener {
            startActivity(Intent(this, SnackBar::class.java))
        }
        viewBinding.btnFloat.setOnClickListener {
            startActivity(Intent(this, FloatingButton::class.java))
        }
        viewBinding.btnIntent.setOnClickListener {
            startActivity(Intent(this, IntentDemo::class.java))
        }
        viewBinding.btnUi.setOnClickListener {
            startActivity(Intent(this, UiDesign::class.java))
        }
        viewBinding.btnRecyclerview.setOnClickListener {
            startActivity(Intent(this, ReyclerView::class.java))
        }
        viewBinding.btnCustomRecycler.setOnClickListener {
            startActivity(Intent(this, CustomRecyclerDemo::class.java))
        }
        viewBinding.btnBottomNav.setOnClickListener {
            startActivity(Intent(this, BottomNav::class.java))
        }
        viewBinding.btnRoom.setOnClickListener {
            startActivity(Intent(this, RoomDatabase::class.java))
        }
        viewBinding.btnInternal.setOnClickListener {
            startActivity(Intent(this, InternalStorage::class.java))
        }
        viewBinding.btnExternal.setOnClickListener {
            startActivity(Intent(this, ExternalStorage::class.java))
        }
        viewBinding.btnNavigation.setOnClickListener {
            startActivity(Intent(this, NavigationDemo::class.java))
        }
        viewBinding.btnCamera.setOnClickListener {
            startActivity(Intent(this, Camera2Api::class.java))
        }
        viewBinding.btnRetrofit.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
        viewBinding.btnContact.setOnClickListener {
            startActivity(Intent(this, ContactUs::class.java))
        }
        viewBinding.btnTab.setOnClickListener {
            startActivity(Intent(this, TabWithViewPager::class.java))
        }
        viewBinding.btnRetrofitApi.setOnClickListener {
            startActivity(Intent(this, RetrofitApi::class.java))
        }
        viewBinding.btnConnectivity.setOnClickListener {
            startActivity(Intent(this, Connectivity::class.java))
        }
        viewBinding.btnBle.setOnClickListener {
            startActivity(Intent(this, DeviceScanActivity::class.java))
        }
        viewBinding.btnBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity4::class.java))
        }
        viewBinding.btnCalc.setOnClickListener {
            startActivity(Intent(this,Calculator::class.java))
        }
        viewBinding.btnNotify.setOnClickListener {
            startActivity(Intent(this,Notification::class.java))
        }
    }
}