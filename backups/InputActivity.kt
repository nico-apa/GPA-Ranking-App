package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.google.firebase.database.DatabaseReference

const val IA : String = "InputActivity"
class InputActivity : AppCompatActivity() {
    private lateinit var inputClass : EditText
    private lateinit var header : TextView
    private lateinit var inputGrade : EditText
    private lateinit var inputCredit : EditText
    private lateinit var enterGradesButton : Button
    private lateinit var homeButton : Button
    private lateinit var rankButton : Button
    private var student = MainActivity.student
    // private var firebase = MainActivity.firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // initializes student class
        student = Student(this)

        header = findViewById(R.id.header)
        inputClass = findViewById(R.id.input_Class)
        inputGrade = findViewById(R.id.input_Grade)
        inputCredit = findViewById(R.id.input_Credit)
        enterGradesButton = findViewById(R.id.enter_grades)
        homeButton = findViewById(R.id.home_page)
        rankButton = findViewById(R.id.rank_page)

        enterGradesButton.setOnClickListener{loadData()}

        homeButton.setOnClickListener{homeActivity()}

        rankButton.setOnClickListener{rankActivity()}

        colorTheme()
    }

    // takes user to home page
    fun homeActivity() {
        finish()
    }

    // takes user to rankings page
    fun rankActivity() {
        var intent : Intent = Intent(this, RanksActivity::class.java)
        startActivity(intent)
    }

    // handles data inputted from user
    fun loadData() {

        var nameClass : String = inputClass.text.toString()
        var grade : Double = inputGrade.text.toString().toDouble()
        var credit : Int = inputCredit.text.toString().toInt()

        student.getCredit(credit)
        student.getGrade(grade)
        student.getNameClass(nameClass)

        var reference : DatabaseReference = MainActivity.firebase.getReference(MainActivity.student.getName())
        reference.setValue(MainActivity.student)

        // sets persistent data
        student.setPreferences(this)

        // Log.w("MainActivity", "class: " + nameClass + " grade: " + grade + " credits: " + credit)
    }

    //Shows what user is logged in and updates color
    fun colorTheme() {
        if(MainActivity.student.getColor() != "") {
            header.setBackgroundColor(MainActivity.student.getColor().toColorInt())
        }
        header.text = MainActivity.student.getName()

        // sets persistent data
        student.setPreferences(this)
    }

    // static method to declare student class
    companion object {
        lateinit var student : Student
    }
}