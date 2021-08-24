package com.example.demoapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerCustomAdapter(var context: Context,var images: Array<Int>,var type: Array<String>,var followers: Array<String>,var tracks: Array<String>,var button:Array<Int>):
    RecyclerView.Adapter<RecyclerCustomAdapter.CustomHolder>() {
    class CustomHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var followers=itemView.findViewById<TextView>(R.id.tv_followers)
        var Nametype=itemView.findViewById<TextView>(R.id.tv_types)
        var track=itemView.findViewById<TextView>(R.id.tv_tracks)
        var followingBtn=itemView.findViewById<Button>(R.id.btn_following)
        var constraint=itemView.findViewById<ConstraintLayout>(R.id.constraint_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.custom_recycler,parent,false)
        return CustomHolder(view)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.followers.text=followers[position]
        holder.Nametype.text=type[position]
        holder.track.text=tracks[position]
        holder.constraint.setBackgroundResource(images[position])
        holder.followingBtn.setBackgroundResource(button[position])
        holder.itemView.setOnClickListener {
            var intent=Intent(context,NextActivity::class.java)
            intent.putExtra("type",type[position])
            intent.putExtra("followers",followers[position])
            intent.putExtra("track",tracks[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return type.size
    }

}
