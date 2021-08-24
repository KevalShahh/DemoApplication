package com.example.demoapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.databinding.ActivityRoomDatabaseBinding

class RoomDatabase : AppCompatActivity() {
    lateinit var viewBinding: ActivityRoomDatabaseBinding
    private var model = ArrayList<RoomModel>()
    private lateinit var adapter: CustomRoomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRoomDatabaseBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        val db = NotesDatabase.getInstance(this)
        val getData = db.notesDao().getAll()
        for (i in 0 until getData.size) {
            model.add(RoomModel(getData[i].title, getData[i].description, getData[i].id))
        }
        adapter = CustomRoomAdapter(this, model)
        viewBinding.roomRv.adapter = adapter
        viewBinding.roomRv.layoutManager = LinearLayoutManager(this)
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter))
        itemTouchHelper.attachToRecyclerView(viewBinding.roomRv)
        viewBinding.roomFloating.setOnClickListener {
            startActivity(Intent(this, AddNotes::class.java))
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = NotesDatabase.getInstance(this)
        when (item.itemId) {
            R.id.item_delete -> {
                db.notesDao().deleteAll()
                adapter.clean()
                Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



