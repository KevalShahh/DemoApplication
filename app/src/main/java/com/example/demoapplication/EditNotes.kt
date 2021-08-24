package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.example.demoapplication.databinding.ActivityEditNotesBinding

class EditNotes : AppCompatActivity() {
    lateinit var viewBinding: ActivityEditNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditNotesBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.edtUpdateTitle.setText(intent.getStringExtra("title").toString())
        viewBinding.edtUpdateDescription.setText(intent.getStringExtra("description").toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var db = NotesDatabase.getInstance(this)
        var title = viewBinding.edtUpdateTitle.text.toString()
        var description = viewBinding.edtUpdateDescription.text.toString()
        var id = intent.getIntExtra("id",0)

        when (item.itemId) {
            R.id.item_update -> {
                db.notesDao().updateData(title, description, id)
                startActivity(Intent(this,RoomDatabase::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}