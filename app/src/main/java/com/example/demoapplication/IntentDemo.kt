package com.example.demoapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoapplication.databinding.ActivityIntentDemoBinding

class IntentDemo : AppCompatActivity() {
    lateinit var viewBinding:ActivityIntentDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityIntentDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnExplicit.setOnClickListener {
            var intent:Intent=Intent(this,MainActivity2::class.java)
            intent.putExtra("Name",viewBinding.edtName.text.toString())
            intent.putExtra("Age",viewBinding.edtAge.text.toString())
            startActivity(intent)
        }
        viewBinding.btnInplicit.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(intent)
        }
        viewBinding.btnIntentfilter.setOnClickListener {
            var intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT,"Demo Application subject")
            intent.putExtra(Intent.EXTRA_TEXT,"Demo Application Text")
            startActivity(intent)
        }
    }
}