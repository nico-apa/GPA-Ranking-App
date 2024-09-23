package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences


class Student {

    private var name: String =  ""
    private var email = ""
    private var grade : Double = 0.0
    private var gpa : Double = 0.0
    private var color : String = ""

    constructor(context : Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        setName(pref.getString(PREFERENCE_NAME, "").toString())
        setEmail(pref.getString(PREFERENCE_EMAIL, "").toString())
        setColor(pref.getString(PREFERENCE_COLOR, "").toString())
        setGpa(pref.getFloat(PREFERENCE_GPA, 4.0f).toDouble()) // Added to pass gpa information to be updated by database later
    }

    constructor(name: String, email: String, gpa: Double, color: String) {
        this.name = name
        this.email = email
        this.gpa = gpa
        this.color = color
    }

    fun setGpa (gpa : Double) {
        this.gpa = gpa
    }
    fun getGpa () : Double {
        return this.gpa
    }

    fun setName(name1 : String) {
        this.name = name1
    }

    fun setEmail(email1 : String) {
        this.email = email1
    }

    fun setColor(color1 : String) {
        this.color = color1
    }

    fun getName() : String {
        return this.name
    }

    fun getEmail() : String {
        return this.email
    }

    fun getColor() : String {
        return this.color
    }


    fun setGrade(grade : Double) {
        this.grade = grade
    }


    fun setPreferences(context : Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()

        editor.putString(PREFERENCE_NAME, name)
        editor.putString(PREFERENCE_EMAIL, email)
        editor.putString(PREFERENCE_COLOR, color)
        editor.putFloat(PREFERENCE_GPA, gpa.toFloat())
        editor.commit()
    }

    override fun toString(): String {
        return "Student(name=$name, email=$email, gpa=$gpa, color=$color)"
    }

    companion object {
        private const val PREFERENCE_NAME : String = "name"
        private const val PREFERENCE_EMAIL : String = "email"
        private const val PREFERENCE_COLOR : String = "color"
        private const val PREFERENCE_GPA : String = "gpa"


    }

}