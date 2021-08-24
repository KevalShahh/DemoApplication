package com.example.demoapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(var context: Context, var model: ArrayList<CustomModel>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView = itemView.findViewById<TextView>(R.id.tv)
        var editText = itemView.findViewById<EditText>(R.id.edt)
        var button = itemView.findViewById<Button>(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.activity_custom_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        // return data.size
        return model.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        /*holder.textView.setText(data[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Selected : "+data[position], Toast.LENGTH_SHORT).show()
        }*/
        var customModel = model[position]
        holder.textView.text = customModel.TextValue
        holder.button.setOnClickListener {
            customModel.setData(holder.editText.text.toString())
            var pos=position
            Log.d("TAG", "onBindViewHolder: "+pos)
            //holder.textView.text = customModel.TextValue
            customModel.setPosition(position)
            notifyDataSetChanged()
        }
    }

}
