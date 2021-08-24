package com.example.demoapplication

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getExternalFilesDirs
import com.example.demoapplication.databinding.ActivityExternalStorageBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class ExternalStorage : AppCompatActivity() {
    lateinit var viewBinding: ActivityExternalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityExternalStorageBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnSavePublic.setOnClickListener {
            savePublicly(viewBinding.root)
        }
        viewBinding.btnSavePrivate.setOnClickListener {
            savePrivately(viewBinding.root)
        }
        viewBinding.btnShowPublic.setOnClickListener {
            showPublicData(viewBinding.root)
        }
        viewBinding.btnShowPrivate.setOnClickListener {
            showPrivateData(viewBinding.root)
        }
    }

    private fun savePublicly(view: View?) {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            23
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            23
        )
        val editTextData: String = viewBinding.edtExternalData.text.toString()
        var folderArray:Array<File> = getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS)
        var folders:File=folderArray[1]
      //  Toast.makeText(this, ""+folders, Toast.LENGTH_SHORT).show()
        val folder: File? = folders
            //getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, "publicData.txt")
        writeTextData(file, editTextData)
        viewBinding.edtExternalData.setText("")
    }

    private fun savePrivately(view: View?) {
        val editTextData: String = viewBinding.edtExternalData.text.toString()
        var folderArray:Array<File> = getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS)
        var folders:File=folderArray[1]
        val folder: File? = folders
            //getExternalFilesDir("ExternalStorageDemo")
        val file = File(folder, "privateData.txt")
        writeTextData(file, editTextData)
        viewBinding.edtExternalData.setText("")
    }

    private fun writeTextData(file: File, data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(data.toByteArray())
            Toast.makeText(this, "Data Stored" + file.absolutePath, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun showPublicData(view: View?) {
        val folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, "publicData.txt")
        val data: String = getdata(file).toString()
        if (data != null) {
            viewBinding.tvDataPublic.text = data
        } else {
            viewBinding.tvDataPublic.text = "No Data Found"
        }
    }

    fun showPrivateData(view: View?) {
        val folder = getExternalFilesDir("ExternalStorageDemo")
        val file = File(folder, "privateData.txt")
        val data: String = getdata(file).toString()
        if (data != null) {
            viewBinding.tvDataPrivate.text = data
        } else {
            viewBinding.tvDataPrivate.text = "No Data Found"
        }
    }

    private fun getdata(myfile: File): String? {
        var fileInputStream: FileInputStream? = null
        try {
            fileInputStream = FileInputStream(myfile)
            var i = -1
            val buffer = StringBuffer()
            while (fileInputStream.read().also { i = it } != -1) {
                buffer.append(i.toChar())
            }
            return buffer.toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }
}