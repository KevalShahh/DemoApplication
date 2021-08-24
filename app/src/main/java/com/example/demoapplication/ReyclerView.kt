package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapplication.databinding.ActivityReyclerViewBinding

class ReyclerView : AppCompatActivity() {
    lateinit var viewBinding: ActivityReyclerViewBinding
     var model= ArrayList<CustomModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityReyclerViewBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        for (i in 1..50) {
            model.add(CustomModel("",0))
        }

        var adapter = CustomRecyclerAdapter(this,model)
        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = LinearLayoutManager(this)
    }
}