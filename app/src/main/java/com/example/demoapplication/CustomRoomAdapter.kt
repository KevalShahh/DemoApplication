package com.example.demoapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomRoomAdapter(var context: Context, var model: ArrayList<RoomModel>) :
    RecyclerView.Adapter<CustomRoomAdapter.NotesViewHolder>() {
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview_1 = itemView.findViewById<TextView>(R.id.tv_title)
        var textview_2 = itemView.findViewById<TextView>(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_room_rv, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.textview_1.text = model[position].titleNotes
        holder.textview_2.text = model[position].descriptionNotes
        holder.itemView.setOnClickListener {
            var intent = Intent(context, EditNotes::class.java)
            intent.putExtra("title", model[position].titleNotes)
            intent.putExtra("description", model[position].descriptionNotes)
            intent.putExtra("id", model[position].idNotes)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    fun deleteItem(position: Int) {
        var db = NotesDatabase.getInstance(context)
        db.notesDao().deleteData(model[position].idNotes)
        model.removeAt(position)
        notifyItemRemoved(position)
        Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
    }

    fun clean() {
        model.clear()
        notifyDataSetChanged()
    }
}
