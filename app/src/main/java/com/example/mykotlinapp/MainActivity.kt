package com.example.mykotlinapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llMain)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//
//
//        }

        val edtWeight = findViewById<EditText>(R.id.edtWeight)
        val edtHeightFt = findViewById<EditText>(R.id.edtHeightft)
        val edtHeightIn = findViewById<EditText>(R.id.edtHeightIn)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtMsg = findViewById<TextView>(R.id.txtMsg)
        val rootView = findViewById<LinearLayout>(R.id.llMain)

        btnCalculate.setOnClickListener {
            val weightText = edtWeight.text.toString()
            val heightFtText = edtHeightFt.text.toString()
            val heightInText = edtHeightIn.text.toString()

            if (weightText.isEmpty() || heightFtText.isEmpty() || heightInText.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = weightText.toDoubleOrNull()
            val heightFt = heightFtText.toDoubleOrNull()
            val heightIn = heightInText.toDoubleOrNull()

            if (weight == null || heightFt == null || heightIn == null) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val heightMeters = (heightFt * 0.3048) + (heightIn * 0.0254)

            if (heightMeters == 0.0) {
                Toast.makeText(this, "Height cannot be zero.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bmi = weight / (heightMeters * heightMeters)
            txtResult.text = String.format("BMI: %.2f", bmi)

            val (healthMessage, backgroundColor) = when {
                bmi < 18.5 -> "You are underweight." to Color.YELLOW
                bmi < 24.9 -> "Your weight is normal." to Color.GREEN
                bmi < 29.9 -> "You are overweight." to Color.parseColor("#FFA500")
                else -> "You are obese." to Color.RED

            }
            txtMsg.text = healthMessage
            rootView.setBackgroundColor(backgroundColor)
        }



    }
}