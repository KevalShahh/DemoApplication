package com.example.demoapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityInternalStorageBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class InternalStorage : AppCompatActivity() {
    private var filename = "demoFile.txt"
    lateinit var viewBinding: ActivityInternalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInternalStorageBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnRead.setOnClickListener {
            readData()
        }
        viewBinding.btnWrite.setOnClickListener {
            writeData()
        }
        viewBinding.btnImage.setOnClickListener {
            imageChooser()
        }
    }

    private fun readData() {
        try {
            val fin: FileInputStream = openFileInput(filename)
            var a: Int
            val temp = StringBuilder()
            while (fin.read().also { a = it } != -1) {
                temp.append(a.toChar())
            }
            viewBinding.tvFileData.setText(temp.toString())
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(this, "Reading " + filename.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun writeData() {
        try {
            val fos: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            val data: String = viewBinding.edtInternalData.text.toString()
            fos.write(data.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        viewBinding.edtInternalData.setText("")
        Toast.makeText(this, "writing in " + filename, Toast.LENGTH_SHORT).show()
    }

    fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                val selectedImageUri: Uri? = data?.data
                if (null != selectedImageUri) {
                    viewBinding.ivImage.setImageURI(selectedImageUri)
                }
            }
        }
    }
}