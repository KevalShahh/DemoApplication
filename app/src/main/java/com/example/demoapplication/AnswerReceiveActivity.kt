package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_answer_receive.*

class AnswerReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_receive)
        tvAnswerReceiveText.text = intent.action
//        tvAnswerReceiveText.text = intent.flags.toString()
    }
}