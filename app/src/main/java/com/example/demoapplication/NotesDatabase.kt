package com.example.demoapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        private var instance: NotesDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NotesDatabase {
            instance = databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "Notes"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            return instance as NotesDatabase
        }
    }
}