package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapplication.databinding.ActivityCustomRecyclerDemoBinding

class CustomRecyclerDemo : AppCompatActivity() {
    lateinit var viewBiding:ActivityCustomRecyclerDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBiding= ActivityCustomRecyclerDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBiding.root)

        var images= arrayOf(R.drawable.dance,R.drawable.jazz,R.drawable.rock,R.drawable.pop,R.drawable.folk)
        var type= arrayOf("DANCE","JAZZ","ROCK","POP","FOLK")
        var followers= arrayOf("27K FOLLOWERS","15K FOLLOWERS","34K FOLLOWERS","20K FOLLOWERS","10K FOLLOWERS")
        var tracks= arrayOf("3754 TRACKS","12357 TRACKS","15219 TRACKS","1234 TRACKS","2546 TRACKS")
        var button= arrayOf(R.drawable.dance_button,R.drawable.jazz_button,R.drawable.rock_button,R.drawable.dance_button,R.drawable.jazz_button)
        var adapter=RecyclerCustomAdapter(this,images,type,followers,tracks,button)
        viewBiding.recyclerCustom.adapter=adapter
        viewBiding.recyclerCustom.layoutManager=LinearLayoutManager(this)
    }
}