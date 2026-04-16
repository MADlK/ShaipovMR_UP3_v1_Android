package com.bignerdranch.android.ShaipovM_v8


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class Sigh_In_Screen : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var butt: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigh_in_screen)

        val shared=getSharedPreferences("share", MODE_PRIVATE)

        email=findViewById(R.id.email)
        pass=findViewById(R.id.password)
        butt=findViewById(R.id.loginButton)

        butt.setOnClickListener{
        if (email.text.toString().trim().isEmpty() || pass.text.toString().trim().isEmpty())
        {
            println("*****************************************  не какашка")
            Toast.makeText(this, "заполните все поля", Toast.LENGTH_SHORT).show()
        }
        else
        {
            println("***************************************** какашка")
            val editor=shared.edit()
            editor.putString("login",email.text.toString())

            editor.putString("password",pass.text.toString())
            editor.apply()

                val intent=Intent(this,MainScreen::class.java)
                startActivity(intent)
        }

        }

    }
}