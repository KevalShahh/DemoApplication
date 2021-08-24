package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_expresso_test.*

class ExpressoTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expresso_test)
    }
    fun onClick(view: View) {

        when (view.id) {
            R.id.changeText -> {
                inputField.setText("Hey")
            }
            R.id.switchActivity ->{
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("input", inputField.text.toString())
                startActivity(intent)
            }
        }
    }
}
