package com.example.demoapplication

class CustomModel (var TextValue:String,var Textposition:Int){

    fun getData(): String {
        return this.TextValue
    }
    fun setData(string: String){
        this.TextValue=string
    }
    fun getPosition():Int{
        return this.Textposition
    }
    fun setPosition(int:Int){
        this.Textposition=int
    }
}
