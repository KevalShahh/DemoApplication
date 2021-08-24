package com.example.demoapplication

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.demoapplication.databinding.ActivityFloatingButtonBinding
import kotlin.properties.Delegates


class FloatingButton : AppCompatActivity() {
    lateinit var viewBinding:ActivityFloatingButtonBinding
    var isFabOpen =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityFloatingButtonBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.fab1.setOnClickListener {
            if (!isFabOpen){
                showFABMenu()
            }
            else{
                closeFABMenu()
            }
        }
        viewBinding.fab2.setOnClickListener {
            Toast.makeText(this, "New group selected", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("ResourceType")
    private fun showFABMenu() {
        isFabOpen = true
        viewBinding.tvFab1.visibility=View.VISIBLE
        viewBinding.fab2.animate().translationY(-200F)
        viewBinding.tvFab2.animate().translationY(-200F)
        viewBinding.tvFab2.visibility=View.VISIBLE
        viewBinding.fab3.animate().translationY(-400F)
        viewBinding.tvFab3.animate().translationY(-400F)
        viewBinding.tvFab3.visibility=View.VISIBLE
        viewBinding.fab4.animate().translationY(-600F)
        viewBinding.tvFab4.animate().translationY(-600F)
        viewBinding.tvFab4.visibility=View.VISIBLE
    }

    private fun closeFABMenu() {
        isFabOpen = false
        viewBinding.tvFab1.visibility=View.INVISIBLE
        viewBinding.fab2.animate().translationY(0F)
        viewBinding.tvFab2.animate().translationY(0F)
        viewBinding.tvFab2.visibility=View.INVISIBLE
        viewBinding.fab3.animate().translationY(0F)
        viewBinding.tvFab3.animate().translationY(0F)
        viewBinding.tvFab3.visibility=View.INVISIBLE
        viewBinding.fab4.animate().translationY(0F)
        viewBinding.tvFab4.animate().translationY(0F)
        viewBinding.tvFab4.visibility=View.INVISIBLE
    }
}