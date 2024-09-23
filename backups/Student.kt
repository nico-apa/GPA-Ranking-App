package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences


class Student {

    private var name: String =  ""
    private var nameClass: String = ""
    private var email = ""
    private var grade : Double = 0.0
    private var credit : Int = 0
    private var color : String = ""

    constructor(context : Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        setName(pref.getString(PREFERENCE_NAME, "").toString())
        setEmail(pref.getString(PREFERENCE_EMAIL, "").toString())
        setColor(pref.getString(PREFERENCE_COLOR, "").toString())
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

    fun getCredit(credit : Int) : Int {
        return credit
    }

    fun getGrade(grade : Double) : Double {
        return grade
    }

    fun getNameClass(nameClass : String) : String {
        return nameClass
    }

    fun setPreferences(context : Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()

        editor.putString(PREFERENCE_NAME, name)
        editor.putString(PREFERENCE_EMAIL, email)
        editor.putString(PREFERENCE_COLOR, color)
        editor.commit()
    }

    companion object {
        private const val PREFERENCE_NAME : String = "name"
        private const val PREFERENCE_EMAIL : String = "email"
        private const val PREFERENCE_COLOR : String = "color"
    }

}