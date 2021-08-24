package com.example.demoapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomRetrofitRvAdapter(var context: RetrofitActivity, var foodlist: List<FoodModel>) : RecyclerView.Adapter<CustomRetrofitRvAdapter.RetroViewHolder>() {
    class RetroViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var id = itemView.findViewById<TextView>(R.id.tv_id_output)!!
        var name=itemView.findViewById<TextView>(R.id.tv_name_output)!!
        var description=itemView.findViewById<TextView>(R.id.tv_description_output)!!
        var price=itemView.findViewById<TextView>(R.id.tv_price_output)!!
        var chef=itemView.findViewById<TextView>(R.id.tv_chef_output)!!
        var image=itemView.findViewById<ImageView>(R.id.img_retro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.custom_retrofit_rv,parent,false)
        return RetroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetroViewHolder, position: Int) {
        holder.id.text = foodlist[position].getId().toString()
        holder.name.text = foodlist[position].getName()!!
        holder.description.text = foodlist[position].getDescription()!!
        holder.price.text = foodlist[position].getPrice().toString()
        holder.chef.text = foodlist[position].getChef()!!
        var pos=holder.position
        Log.d("TAG", "onBindViewHolder: "+pos)
        Glide.with(context).load(foodlist[holder.adapterPosition].getThumbnail()).into(holder.image)
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }
}
