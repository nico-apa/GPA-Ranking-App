package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt

const val RA : String = "RanksActivity"

class RanksActivity : AppCompatActivity() {
    private lateinit var homeButton : Button
    private lateinit var previousButton : Button
    private lateinit var header : TextView
    //private var firebase = MainActivity.firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranks)

        header = findViewById(R.id.header)
        homeButton = findViewById(R.id.home_page)
        previousButton = findViewById(R.id.input_page)

        homeButton.setOnClickListener{homeActivity()}

        previousButton.setOnClickListener{inputActivity()}

        updateColorTheme()

    }

    // takes user to home page
    fun homeActivity() {
        var intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // takes user to input page
    fun inputActivity() {
        finish()
    }

    //Shows what user is logged in and updates color
    fun updateColorTheme() {
        var student : Student = MainActivity.student

        /*
        if (student.getColor() == null && student.getName() == null) {
            Log.w(RA, "variables are null")
        } else {
            Log.w(RA, "User selected color: " + student.getColor())
            Log.w(RA, "User name: " + student.getName())
        }
        */

        if(student.getColor() != "") {
            header.setBackgroundColor(student.getColor().toColorInt())
        }
        header.text = student.getName()

        // sets persistent data
        student.setPreferences(this)
    }

}