package com.example.groupproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
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
    private lateinit var enterButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // initializes student class
        student = Student(this)

        header = findViewById(R.id.header)
        // inputGrade = findViewById(R.id.input_Grade)
        // enterGradesButton = findViewById(R.id.enter_grades)
        homeButton = findViewById(R.id.home_page)
        rankButton = findViewById(R.id.rank_page)
        enterButton = findViewById(R.id.enter_button)

        //  enterGradesButton.setOnClickListener{loadData()}

        homeButton.setOnClickListener{homeActivity()}
        rankButton.setOnClickListener{rankActivity()}
        enterButton.setOnClickListener{ratingBarMsg()}

        colorTheme()
        createAd()
    }

    fun ratingBarMsg() {
        var toast : Toast = Toast.makeText(this, "Thank you for rating app!", Toast.LENGTH_LONG)
        toast.show()
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
//    fun loadData() {
//
//
//        var grade : Double = inputGrade.text.toString().toDouble()
//        var credit : Int = inputCredit.text.toString().toInt()
//
//        student.setCredit(credit)
//        student.setGrade(grade)
//
//
//        // sets persistent data
//        student.setPreferences(this)
//
//        // Log.w("MainActivity", "class: " + nameClass + " grade: " + grade + " credits: " + credit)
//    }

    //Shows what user is logged in and updates color
    fun colorTheme() {
        if(MainActivity.student.getColor() != "") {
            header.setBackgroundColor(MainActivity.student.getColor().toColorInt())
        }
        header.text = MainActivity.student.getName()

        // sets persistent data
        student.setPreferences(this)
    }

    fun createAd() {
        var adView = AdView(this)
        var adSize : AdSize = AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT)
        adView.setAdSize(adSize)

        var adUnitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId

        var builder : AdRequest.Builder = AdRequest.Builder()
        builder.addKeyword("fitness").addKeyword("workout")
        var adRequest : AdRequest = builder.build()

        var adLayout : LinearLayout = findViewById(R.id.ad_view)
        adLayout.addView(adView)
        adView.loadAd(adRequest)
    }

    // static method to declare student class
    companion object {
        lateinit var student : Student
    }
}