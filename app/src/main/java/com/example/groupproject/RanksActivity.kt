package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.example.groupproject.InputActivity.Companion.student
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

const val RA: String = "RanksActivity"

class RanksActivity : AppCompatActivity() {
    private lateinit var homeButton: Button
    private lateinit var previousButton: Button
    private lateinit var header: TextView
    private lateinit var reference: DatabaseReference
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var textView7: TextView
    private lateinit var textView8: TextView
    private lateinit var textView9: TextView
    private lateinit var textView10: TextView
    private lateinit var textView11: TextView
    private lateinit var textView12: TextView
    private lateinit var textView13: TextView
    private lateinit var textView14: TextView
    private lateinit var textView15: TextView
    private lateinit var textView16: TextView
    private lateinit var textView17: TextView
    private lateinit var textView18: TextView
    private lateinit var textView19: TextView
    private lateinit var textView20: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranks)

        header = findViewById(R.id.header)
        homeButton = findViewById(R.id.home_page)
        previousButton = findViewById(R.id.input_page)

        // Limiting the Leaderboard to 10 users, 1-10 is for the names, 11-20 for the GPA's
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        textView6 = findViewById(R.id.textView6)
        textView7 = findViewById(R.id.textView7)
        textView8 = findViewById(R.id.textView8)
        textView9 = findViewById(R.id.textView9)
        textView10 = findViewById(R.id.textView10)
        textView11 = findViewById(R.id.textView11)
        textView12 = findViewById(R.id.textView12)
        textView13 = findViewById(R.id.textView13)
        textView14 = findViewById(R.id.textView14)
        textView15 = findViewById(R.id.textView15)
        textView16 = findViewById(R.id.textView16)
        textView17 = findViewById(R.id.textView17)
        textView18 = findViewById(R.id.textView18)
        textView19 = findViewById(R.id.textView19)
        textView20 = findViewById(R.id.textView20)

        homeButton.setOnClickListener { homeActivity() }
        previousButton.setOnClickListener { inputActivity() }

        // naming the directory inside the database as (students
        reference = FirebaseDatabase.getInstance().getReference("students")

        fetchStudentData()
        updateColorTheme()
        createAd()
    }
    // different way to do DataListener, example from class introduced bugs
    private fun fetchStudentData() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val studentList = mutableListOf<Pair<String, Double>>()

                try {
                    for (studentSnapshot in snapshot.children) { //getting the children of the list
                        val student = studentSnapshot.value.toString()
                        // Parsing the string returned from the Database, not in proper JSON format but close
                        val name = student.substringAfter("name=").substringBefore(",")
                        val gpa = student.substringAfter("gpa=").substringBefore(",")

                        // adding parsed names and GPA to the list as pairs
                        studentList.add(Pair(name, gpa.toDouble()))
                    }
                } catch (e: Exception) {
                }

                // Sorting the list by GPA in descending order
                val sortedStudentList = studentList.sortedByDescending { student -> student.second}

                // Updating the text views for the Names
                if (sortedStudentList.isNotEmpty()) textView1.text = sortedStudentList.getOrNull(0)?.first ?: "Unknown"
                if (sortedStudentList.size > 1) textView2.text = sortedStudentList.getOrNull(1)?.first ?: "Unknown"
                if (sortedStudentList.size > 2) textView3.text = sortedStudentList.getOrNull(2)?.first ?: "Unknown"
                if (sortedStudentList.size > 3) textView4.text = sortedStudentList.getOrNull(3)?.first ?: "Unknown"
                if (sortedStudentList.size > 4) textView5.text = sortedStudentList.getOrNull(4)?.first ?: "Unknown"
                if (sortedStudentList.size > 5) textView6.text = sortedStudentList.getOrNull(5)?.first ?: "Unknown"
                if (sortedStudentList.size > 6) textView7.text = sortedStudentList.getOrNull(6)?.first ?: "Unknown"
                if (sortedStudentList.size > 7) textView8.text = sortedStudentList.getOrNull(7)?.first ?: "Unknown"
                if (sortedStudentList.size > 8) textView9.text = sortedStudentList.getOrNull(8)?.first ?: "Unknown"
                if (sortedStudentList.size > 9) textView10.text = sortedStudentList.getOrNull(9)?.first ?: "Unknown"

                // Updating the text views for the GPAs
                if (sortedStudentList.isNotEmpty()) textView11.text = sortedStudentList.getOrNull(0)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 1) textView12.text = sortedStudentList.getOrNull(1)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 2) textView13.text = sortedStudentList.getOrNull(2)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 3) textView14.text = sortedStudentList.getOrNull(3)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 4) textView15.text = sortedStudentList.getOrNull(4)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 5) textView16.text = sortedStudentList.getOrNull(5)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 6) textView17.text = sortedStudentList.getOrNull(6)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 7) textView18.text = sortedStudentList.getOrNull(7)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 8) textView19.text = sortedStudentList.getOrNull(8)?.second.toString() ?: "Unknown"
                if (sortedStudentList.size > 9) textView20.text = sortedStudentList.getOrNull(9)?.second.toString() ?: "Unknown"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(RA, "Failed to read value.", error.toException())
            }
        })
    }

    private fun homeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun inputActivity() {
        finish()
    }

    private fun updateColorTheme() {
        val student = MainActivity.student

        if (student.getColor() == null && student.getName() == null) {
            Log.w(RA, "variables are null")
        } else {
            Log.w(RA, "User selected color: " + student.getColor())
            Log.w(RA, "User name: " + student.getName())
        }

        if (student.getColor() != "") {
            header.setBackgroundColor(student.getColor().toColorInt())
        }
        header.text = student.getName()

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
}