package com.example.lab1helloandroid

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = findViewById<TextView>(R.id.textViewMessage)
        val button = findViewById<Button>(R.id.buttonChangeText)

        button.setOnClickListener {
            message.text = getString(R.string.button_clicked)
        }
    }
}