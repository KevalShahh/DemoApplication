package com.example.demoapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.demoapplication.databinding.ActivitySnackBarBinding
import com.google.android.material.snackbar.Snackbar

class SnackBar : AppCompatActivity() {
    lateinit var viewBindnig: ActivitySnackBarBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBindnig = ActivitySnackBarBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBindnig.root)

        /* viewBindnig.snack.setOnClickListener {
             Snackbar.make(viewBindnig.root,"button clicked",Snackbar.LENGTH_LONG).setBackgroundTint(R.color.teal_200).setTextColor(R.color.purple_500).show()
         }*/
        viewBindnig.floatingbtn.setOnClickListener {
            Snackbar.make(viewBindnig.root, "floating button", Snackbar.LENGTH_SHORT).show()
        }
        viewBindnig.snack.setOnClickListener {
            var snackbar = Snackbar.make(viewBindnig.root, "", Snackbar.LENGTH_SHORT)
            var customsnackview =
                LayoutInflater.from(this).inflate(R.layout.activity_custom_snack, null)
            var snacklayout: Snackbar.SnackbarLayout = snackbar.view as Snackbar.SnackbarLayout
            snacklayout.setPadding(0, 0, 0, 0)
            snacklayout.addView(customsnackview)
            customsnackview.findViewById<Button>(R.id.btn_snack).setOnClickListener {
                snackbar.dismiss()
                Toast.makeText(this, "snackbar dismissed", Toast.LENGTH_SHORT).show()
            }
            snackbar.show()
        }

    }
}