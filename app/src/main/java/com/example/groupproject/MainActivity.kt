package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdSize
//import com.google.android.gms.ads.AdView

const val MA : String = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var inputName : EditText
    private lateinit var inputEmail : EditText
    private lateinit var inputColor : Spinner
    private lateinit var nextButton : Button
    private lateinit var rankingsButton : Button
    private lateinit var header : TextView
    private lateinit var inputGpa : EditText
    private lateinit var reference : DatabaseReference
    // private lateinit var students : ArrayList<String>
    //private lateinit var adapter :ArrayAdapter <String>
    private lateinit var adView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializes student class
        student = Student(this)

        header = findViewById(R.id.header)
        inputName = findViewById(R.id.input_Name)
        inputEmail = findViewById(R.id.input_Email)
        inputColor = findViewById(R.id.input_color)
        nextButton = findViewById(R.id.next_page)
        rankingsButton = findViewById(R.id.rankings_page)
        inputGpa = findViewById(R.id.input_Gpa)

        firebase = FirebaseDatabase.getInstance()
        reference = firebase.getReference("students")

        nextButton.setOnClickListener{nextActivity()}

        rankingsButton.setOnClickListener{rankActivity()}

        var listener : DataListener = DataListener( )
        reference.addValueEventListener( listener )

        // creates ad
        createAd()
    }

    fun createAd() {
        adView = AdView(this)
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

    override fun onResume() {
        super.onResume()
        //adView.resume()
    }

    override fun onPause() {
        //adView.pause()
        super.onPause()
    }

    override fun onDestroy() {
        //  adView.destroy()
        super.onDestroy()
    }

    // takes user to input page
    fun nextActivity() {
        updateMA()

        var intent : Intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }

    // takes user to rankings page
    fun rankActivity() {
        var intent : Intent = Intent(this, RanksActivity::class.java)
        startActivity(intent)
    }

    fun updateMA() {
        val name = inputName.text.toString()
        val email = inputEmail.text.toString()
        val color = inputColor.selectedItem.toString()
        val gpa = inputGpa.text.toString()

        student.setName(name)
        student.setColor(color)
        student.setEmail(email)
        student.setGpa(gpa.toDouble())

        // Use email as the key to ensure uniqueness, need to replace inorder to make unique,
        reference.child(email.replace(".", ",")).setValue(student)

        colorTheme()

        // Set persistent data
        student.setPreferences(this)
    }

    //Shows what user is logged in and updates color
    fun colorTheme() {
        if(student.getColor() != "") {
            header.setBackgroundColor(student.getColor().toColorInt())
        }
        header.text = student.getName()

        // sets persistent data
        student.setPreferences(this)
    }

    inner class DataListener: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            Log.w("MainActivity", "Inside onDataChange")
            if( snapshot.value != null ) {
                Log.w("MainActivity", "value is:" + snapshot.value.toString())
            }

        }

        override fun onCancelled(error: DatabaseError) {

            Log.w("MainActivity", "Inside onCancelled")
        }
    }

    companion object{
        lateinit var firebase : FirebaseDatabase
        lateinit var student: Student
    }
}