package org.example

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val tv = findViewById<TextView>(R.id.tvDisplay)

        findViewById<Button>(R.id.btn0).setOnClickListener { tv.append("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { tv.append("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { tv.append("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { tv.append("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { tv.append("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { tv.append("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { tv.append("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { tv.append("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { tv.append("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { tv.append("9") }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { tv.append("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { tv.append("-") }
        findViewById<Button>(R.id.btnMult).setOnClickListener { tv.append("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { tv.append("/") }

        findViewById<Button>(R.id.btnClear).setOnClickListener { tv.text = "" }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            val s = tv.text.toString()

            if (s.contains("+")) {
                val p = s.split("+")
                val res = p[0].toInt() + p[1].toInt()
                tv.text = res.toString()
            } else if (s.contains("-")) {
                val p = s.split("-")
                val res = p[0].toInt() - p[1].toInt()
                tv.text = res.toString()
            } else if (s.contains("*")) {
                val p = s.split("*")
                val res = p[0].toInt() * p[1].toInt()
                tv.text = res.toString()
            } else if (s.contains("/")) {
                val p = s.split("/")
                if (p[1] != "0") {
                    val res = p[0].toInt() / p[1].toInt()
                    tv.text = res.toString()
                } else {
                    tv.text = "Error"
                }
            }
        }
    }
}