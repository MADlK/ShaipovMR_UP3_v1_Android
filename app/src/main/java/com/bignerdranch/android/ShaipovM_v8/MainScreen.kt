package com.bignerdranch.android.ShaipovM_v8

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton

class MainScreen : AppCompatActivity() {
    private lateinit var yandex:ImageButton
    private lateinit var delivery:ImageButton
    private lateinit var samokat:ImageButton
    private lateinit var cuper:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val shared=getSharedPreferences("share", MODE_PRIVATE)

        yandex=findViewById(R.id.yandex)
        delivery=findViewById(R.id.delivery)
        samokat=findViewById(R.id.samokat)
        cuper=findViewById(R.id.cuper)

        yandex.setOnClickListener{
            val name="Доставка Yandex"
            val editor=shared.edit()
            editor.putString("service",name)
            editor.apply()

            val intent=Intent(this,menu_screen::class.java)
            startActivity(intent)
        }

        delivery.setOnClickListener{
            val name="Доставка delivery club"
            val editor=shared.edit()
            editor.putString("service",name)
            editor.apply()


            val intent=Intent(this,menu_screen::class.java)
            startActivity(intent)
        }

        samokat.setOnClickListener{
            val name="Доставка Самокат"
            val editor=shared.edit()
            editor.putString("service",name)
            editor.apply()


            val intent=Intent(this,menu_screen::class.java)
            startActivity(intent)
        }

        cuper.setOnClickListener{
            val name="Доставка Купер"
            val editor=shared.edit()
            editor.putString("service",name)
            editor.apply()


            val intent=Intent(this,menu_screen::class.java)
            startActivity(intent)
        }


    }
}