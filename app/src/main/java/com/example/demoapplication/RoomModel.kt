package com.example.demoapplication

class RoomModel(var titleNotes:String , var descriptionNotes:String , var idNotes:Int) {
    fun getTitle():String{
        return this.titleNotes
    }
    fun setTitle(string: String){
        this.descriptionNotes=string
    }
    fun getDesc():String{
        return this.descriptionNotes
    }
    fun setDesc(string: String){
        this.titleNotes=string
    }
    fun getId():Int{
        return this.idNotes
    }
    fun setId(int: Int){
        this.idNotes=int
    }
}