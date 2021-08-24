package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityNextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityNextBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.tvGenreType.text=intent.getStringExtra("type")
        viewBinding.tvGenreFollowers.text=intent.getStringExtra("followers")
        viewBinding.tvGenreTracks.text=intent.getStringExtra("track")
    }
}
