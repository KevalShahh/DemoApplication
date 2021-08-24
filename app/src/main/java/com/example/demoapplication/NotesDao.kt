package com.example.demoapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM notesTable ORDER BY Title ASC")
    fun getAll():List<NotesEntity>

    @Insert
    fun insertData(notesEntity: NotesEntity)

    @Query("DELETE FROM notesTable")
    fun deleteAll()

    @Query("UPDATE notesTable SET Title=:title , Description=:description WHERE id=:ID")
    fun updateData(title:String,description:String,ID:Int)

    @Query("DELETE FROM notesTable WHERE id=:ID")
    fun deleteData(ID: Int)
}