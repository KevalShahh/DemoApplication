package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

class AddNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var db=NotesDatabase.getInstance(this)
        when(item.itemId){
            R.id.item_save->{
                var noteTitle=findViewById<EditText>(R.id.edt_title).text.toString()
                var noteDescription=findViewById<EditText>(R.id.edt_description).text.toString()
                db.notesDao().insertData(NotesEntity(0,noteTitle,noteDescription))
                Toast.makeText(this, "Notes saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,RoomDatabase::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}