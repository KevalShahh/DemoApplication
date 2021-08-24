package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.demoapplication.databinding.ActivityCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class Calculator : AppCompatActivity() {
    lateinit var viewBinding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCalculatorBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        /*viewBinding.btnEquals.setOnClickListener {
            val expression = ExpressionBuilder(viewBinding.edtCalc.text.toString()).build()
            val result = expression.evaluate()
            viewBinding.tvCalc.text = result.toString()
        }*/
        viewBinding.btnClear.setOnClickListener {
            viewBinding.tvCalc.text = ""
            viewBinding.edtCalc.text = null
        }

        viewBinding.btnEquals.setOnClickListener {
            val result = viewBinding.edtCalc.text.toString()
            if (result.substring(1).contains("+") && !(result.substring(1)
                    .contains("-")) && !(result.substring(1).contains("*")) && !(result.substring(1)
                    .contains("/"))
            ) {
                val index = result.indexOf("+")
                val sum =
                    result.substring(0, index).toFloat() + result.substring(index + 1).toFloat()
                viewBinding.tvCalc.text = sum.toString()
            } else if (result.substring(1).contains("-") && !(result.substring(1)
                    .contains("+")) && !(result.substring(1).contains("*")) && !(result.substring(1)
                    .contains("/"))
            ) {
                val index = result.indexOf("-")
                val sub =
                    result.substring(0, index).toFloat() - result.substring(index + 1).toFloat()
                viewBinding.tvCalc.text = sub.toString()
            } else if (result.substring(1).contains("*") && !(result.substring(1)
                    .contains("+")) && !(result.substring(1).contains("-")) && !(result.substring(1)
                    .contains("/"))
            ) {
                val index = result.indexOf("*")
                val mul =
                    result.substring(0, index).toFloat() * result.substring(index + 1).toFloat()
                viewBinding.tvCalc.text = mul.toString()
            } else if (result.substring(1).contains("/") && !(result.substring(1)
                    .contains("+")) && !(result.substring(1).contains("-")) && !(result.substring(1)
                    .contains("*"))
            ) {
                val index = result.indexOf("/")
                val div =
                    result.substring(0, index).toFloat() / result.substring(index + 1).toFloat()
                viewBinding.tvCalc.text = div.toString()
            } else if (result.substring(1).contains("+") && result.substring(1).contains("-")) {

                val f11 = result.substringBefore("+").substringBefore("-")
                val f22 = result.substringAfter("+").substringBefore("-")
                val f33 = result.substringAfter("-").substringBefore("+")

                val res = f11.toFloat() + f22.toFloat() - f33.toFloat()
                viewBinding.tvCalc.text = res.toString()
            } else if (result.substring(1).contains("+") && result.substring(1).contains("*")) {
                try {
                    val f11 = result.substringAfter("+")
                    val f22 = result.substringAfter("*").substringBefore("+")
                    val f33 = result.substringBefore("*").substringAfter("+")

                    val res = f33.toFloat() * f22.toFloat() + f11.toFloat()
                    viewBinding.tvCalc.text = res.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    try {
                        val f11 = result.substringBefore("+")
                        val f22 = result.substringAfter("*").substringBefore("+")
                        val f33 = result.substringBefore("*").substringAfter("+")

                        val res = f33.toFloat() * f22.toFloat() + f11.toFloat()
                        viewBinding.tvCalc.text = res.toString()
                    } catch (e: Exception) {
                        Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (result.substring(1).contains("+") && result.substring(1).contains("/")) {
                try {
                    val f11 = result.substringAfter("+")
                    val f22 = result.substringAfter("/").substringBefore("+")
                    val f33 = result.substringBefore("/").substringAfter("+")
                    if (f22 == "0") {
                        viewBinding.tvCalc.text = "infinite"
                    }
                    val res = f33.toFloat() / f22.toFloat() + f11.toFloat()
                    viewBinding.tvCalc.text = res.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    try {
                        val f11 = result.substringBefore("+")
                        val f22 = result.substringAfter("/").substringBefore("+")
                        val f33 = result.substringBefore("/").substringAfter("+")
                        if (f22 == "0") {
                            viewBinding.tvCalc.text = "infinite"
                        }
                        val res = f33.toFloat() / f22.toFloat() + f11.toFloat()
                        viewBinding.tvCalc.text = res.toString()
                    } catch (e: Exception) {
                        Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (result.substring(1).contains("*") && result.substring(1).contains("/")) {
                try {
                    val f11 = result.substringAfter("/")
                    val f22 = result.substringAfter("*").substringBefore("/")
                    val f33 = result.substringBefore("*").substringAfter("/")
                    if (f11 == "0") {
                        viewBinding.tvCalc.text = "infinite"
                    }
                    val res = f22.toFloat() * f33.toFloat() / f11.toFloat()
                    viewBinding.tvCalc.text = res.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    try {
                        val f11 = result.substringBefore("/")
                        val f22 = result.substringAfter("*").substringBefore("/")
                        val f33 = result.substringBefore("*").substringAfter("/")
                        if (f22 == "0" || f33 == "0") {
                            viewBinding.tvCalc.text = "infinite"
                        }
                        val res = f11.toFloat() / f33.toFloat() * f22.toFloat()
                        viewBinding.tvCalc.text = res.toString()
                    } catch (e: Exception) {
                        Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (result.substring(1).contains("*") && result.substring(1).contains("-")) {
                try {
                    val f11 = result.substringBefore("-")
                    val f22 = result.substringAfter("*").substringBefore("-")
                    val f33 = result.substringAfter("-").substringBefore("*")

                    val res = f11.toFloat() - f33.toFloat() * f22.toFloat()
                    viewBinding.tvCalc.text = res.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    try {
                        val f11 = result.substringBefore("*")
                        val f22 = result.substringAfter("*").substringBefore("-")
                        val f33 = result.substringAfter("-").substringBefore("*")

                        val res = f11.toFloat() * f22.toFloat() - f33.toFloat()
                        viewBinding.tvCalc.text = res.toString()
                    } catch (e: Exception) {
                        Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (result.substring(1).contains("-") && result.substring(1).contains("/")) {
                try {
                    val f11 = result.substringAfter("/")
                    val f22 = result.substringAfter("-").substringBefore("/")
                    val f33 = result.substringBefore("-").substringAfter("/")
                    if (f11 == "0") {
                        viewBinding.tvCalc.text = "infinite"
                    }
                    val res = f33.toFloat() - f22.toFloat() / f11.toFloat()
                    viewBinding.tvCalc.text = res.toString()
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    try {
                        val f11 = result.substringBefore("/")
                        val f22 = result.substringAfter("-").substringBefore("/")
                        val f33 = result.substringBefore("-").substringAfter("/")
                        if (f33 == "0") {
                            viewBinding.tvCalc.text = "infinite"
                        }
                        val res = f11.toFloat() / f33.toFloat() - f22.toFloat()
                        viewBinding.tvCalc.text = res.toString()
                    } catch (e: Exception) {
                        Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun append(it: View) {
        viewBinding.edtCalc.append((it as Button).text)
    }
}